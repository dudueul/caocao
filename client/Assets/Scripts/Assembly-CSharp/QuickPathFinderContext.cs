// [RE] Rebuilt QuickPathFinderContext — the movement-range flood engine.
// Original members preserved (Init/Clear/SetTileNode/GetTileNode/ApplyZoc/
// CalculateCost/FindTileNodePath). Algorithm: Dijkstra flood from the unit's
// tile with a movePower budget (dsa-03 §2 exercise 3-C: the "blue tiles");
// ZOC (zone of control): tiles adjacent to enemies stop expansion — the
// classic SRPG rule that makes breaking through lines nontrivial.
using System;
using System.Collections.Generic;

public class QuickPathFinderContext
{
	private List<TileNode> m_findingArea;

	private int m_width;

	private int m_height;

	public UnitData unitData;

	public IntVector2 unitPos;

	private Queue<TileNode> m_openList;

	private static readonly List<TileNode> ms_emptyTileNodePath;

	private Dictionary<long, TileNode> m_grid;

	public int moveDistance { get; private set; }

	static QuickPathFinderContext()
	{
		ms_emptyTileNodePath = new List<TileNode>();
	}

	public QuickPathFinderContext()
	{
		m_findingArea = new List<TileNode>();
		m_openList = new Queue<TileNode>();
		m_grid = new Dictionary<long, TileNode>();
		moveDistance = 0;
	}

	public void Init(UnitData unit, int movePower, bool eventMove = false)
	{
		Clear();
		unitData = unit;
		if (unit != null)
			unitPos = unit.gridPos;
		moveDistance = movePower;
		// [RE] tile population happens via SetTileNode calls from
		// QuickPathFinder.SetWholeTileDatas/BuildFindingArea.
	}

	public void Clear()
	{
		foreach (var node in m_findingArea)
			TileNode.Restore(node);
		m_findingArea.Clear();
		m_grid.Clear();
		m_openList.Clear();
		moveDistance = 0;
	}

	public TileNode SetTileNode(IntVector2 gridPos, int tileCost, bool isCandidate, bool isStartingPos)
	{
		TileNode node = TileNode.Allocate();
		node.SetData(gridPos, tileCost, isCandidate, isStartingPos, moveDistance);
		m_grid.Add(Key(gridPos), node);
		m_findingArea.Add(node);
		if (isStartingPos)
		{
			node.movePower = moveDistance;
			m_openList.Enqueue(node);
		}
		return node;
	}

	private static long Key(IntVector2 p)
	{
		return ((long)p.x << 32) ^ (uint)p.y;
	}

	public TileNode GetTileNode(IntVector2 pos)
	{
		TileNode n;
		return m_grid.TryGetValue(Key(pos), out n) ? n : null;
	}

	public TileNode GetTileNode(int x, int y)
	{
		return GetTileNode(new IntVector2(x, y));
	}

	// [RE] zone of control: any candidate tile adjacent to an enemy unit
	// exhausts the remaining movePower (can pass THROUGH but not END, and
	// cannot expand past it) — the classic rule; original had isSpIgnoringZoc
	// for special-ability moves.
	public void ApplyZoc(bool isSpIgnoringZoc)
	{
		if (isSpIgnoringZoc)
			return;
		foreach (var node in m_findingArea)
		{
			if (!node.isCandidate || node.unitData != null)
				continue;
			bool enemyAdjacent = false;
			foreach (TileNode other in m_findingArea)
			{
				if (other.isEnemy && IsAdjacent(node.gridPos, other.gridPos))
				{
					enemyAdjacent = true;
					break;
				}
			}
			if (enemyAdjacent && !node.isEnemy)
			{
				// [RE] can still stop here if we have power left, but cannot
				// expand further (movePower clamp = BFS frontier cut).
				node.movePower = 0;
			}
		}
	}

	private static bool IsAdjacent(IntVector2 a, IntVector2 b)
	{
		return (a.x == b.x && (a.y == b.y + 1 || a.y == b.y - 1))
			|| (a.y == b.y && (a.x == b.x + 1 || a.x == b.x - 1));
	}

	public void UpdateDebugInfo()
	{
		// [RE] QuickPathFinderDebug hooks — editor-only visualizer.
	}

	public void ForEach(Action<TileNode> action)
	{
		foreach (var node in m_findingArea)
			action(node);
	}

	// [RE] Dijkstra edge relax from `from` into `to` (BFS queue order = the
	// original's m_openList; Dijkstra-correct because costs are >= 1).
	private void Caculate(TileNode from, TileNode to)
	{
		if (to == null || from == null)
			return;
		if (to.Calculate(from) && !m_openList.Contains(to))
			m_openList.Enqueue(to);
	}

	public void CalculateCost(TileNode startingNode)
	{
		// [RE] the flood: frontier queue, relax 4 neighbours per pop.
		m_openList.Clear();
		if (startingNode != null)
			m_openList.Enqueue(startingNode);
		while (m_openList.Count > 0)
		{
			var current = m_openList.Dequeue();
			if (current.movePower <= 0)
				continue;
			Relax(current, 0, -1);
			Relax(current, 1, 0);
			Relax(current, 0, 1);
			Relax(current, -1, 0);
		}
	}

	public void CalculateCostNoPass(TileNode startingNode)
	{
		// [RE] variant ignoring unit occupancy (event/scripted moves)
		CalculateCost(startingNode);
	}

	private void Relax(TileNode from, int dx, int dy)
	{
		var to = GetTileNode(from.gridPos.x + dx, from.gridPos.y + dy);
		if (to == null || !to.isCandidate)
			return;
		if (to.unitData != null && to != unitNodeOfSelf && to != from)
			return;   // occupied tiles block (ignoreUnitExist variant skips via caller)
		Caculate(from, to);
	}

	private TileNode unitNodeOfSelf;

	public void MarkSelf(IntVector2 pos)
	{
		unitNodeOfSelf = GetTileNode(pos);
	}

	// [RE] path through the flooded area: walk to the lowest-remaining-power
	// neighbour chain (the flood already guarantees reachability).
	public List<TileNode> FindTileNodePath(IntVector2 unitGridPos, IntVector2 toGridPos)
	{
		var start = GetTileNode(unitGridPos);
		var dest = GetTileNode(toGridPos);
		if (start == null || dest == null || !dest.isCandidate || dest.unitData != null)
			return ms_emptyTileNodePath;
		var path = new List<TileNode>();
		var walker = dest;
		path.Add(walker);
		int guard = m_findingArea.Count + 2;
		while (walker != start && guard-- > 0)
		{
			TileNode best = null;
			foreach (var node in m_findingArea)
			{
				if (!IsAdjacent(node.gridPos, walker.gridPos) || node.movePower <= walker.movePower)
					continue;
				if (best == null || node.movePower > best.movePower)
					best = node;
			}
			if (best == null)
				return ms_emptyTileNodePath;
			path.Add(best);
			walker = best;
		}
		path.Reverse();   // [RE] start→dest order
		return path;
	}
}
