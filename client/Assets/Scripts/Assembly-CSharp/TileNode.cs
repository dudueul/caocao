// [RE] Rebuilt TileNode — one BFS node of the movement-range flood
// ("blue tiles"). Original design preserved: pool elements (Thingsoft.Pool),
// adjacent slots (top/right/down/left), per-hash score cache (AI scoring),
// Calculate() relaxes cost from a neighbour like Dijkstra edges.
using System.Collections.Generic;
using Thingsoft;

public class TileNode : IPoolElement
{
	public enum Adjacent
	{
		start = 0,
		top = start,
		right = 1,
		down = 2,
		left = 3,
		num = 4
	}

	public int tileCost;

	public UnitData unitData;

	public bool isCandidate;

	public IntVector2 gridPos;

	public bool isEnemy;

	public bool isOpenState;

	private int m_left;

	private int m_right;

	private int m_top;

	private int m_down;

	public Dictionary<int, float> m_scores;

	private static bool initPool;

	private byte restored;

	private static readonly Queue<TileNode> ms_pool = new Queue<TileNode>();

	public bool isMovableTile
	{
		get
		{
			return isCandidate && !isEnemy && unitData == null;
		}
	}

	public int movePower { get; private set; }

	public int ignoreZocMovePower { get; set; }

	public int pdTileCost { get; set; }

	// [RE] per-unit score cache keyed by unit hash — the AI scoring surface
	// (AI evaluated every reachable tile per unit; see EnumData.aiType).
	public Dictionary<int, float> m_scores;

	public float get_Item(int hashCode)
	{
		float v;
		return (m_scores != null && m_scores.TryGetValue(hashCode, out v)) ? v : 0f;
	}

	public void set_Item(int hashCode, float value)
	{
		if (m_scores == null)
			m_scores = new Dictionary<int, float>();
		m_scores[hashCode] = value;
	}

	// [RE] neighbour slot access by adjacency direction
	public int get_Item(Adjacent adjType)
	{
		switch (adjType)
		{
			case Adjacent.top: return m_top;
			case Adjacent.right: return m_right;
			case Adjacent.down: return m_down;
			case Adjacent.left: return m_left;
			default: return 0;
		}
	}

	private void set_Item(Adjacent adjType, int value)
	{
		switch (adjType)
		{
			case Adjacent.top: m_top = value; break;
			case Adjacent.right: m_right = value; break;
			case Adjacent.down: m_down = value; break;
			case Adjacent.left: m_left = value; break;
		}
	}

	private TileNode()
	{
		restored = 0;
		m_scores = new Dictionary<int, float>();
		Clear();
	}

	private void Clear()
	{
		tileCost = 0;
		unitData = null;
		isCandidate = false;
		isEnemy = false;
		isOpenState = false;
		movePower = 0;
		ignoreZocMovePower = 0;
		pdTileCost = 0;
		if (m_scores != null)
			m_scores.Clear();
	}

	public override bool Equals(object obj)
	{
		var other = obj as TileNode;
		return other != null && other.gridPos == gridPos;
	}

	public override int GetHashCode()
	{
		return gridPos.GetHashCode();
	}

	public override string ToString()
	{
		return string.Format("Tile({0},{1}) cost={2}{3}", gridPos.x, gridPos.y, tileCost,
			unitData != null ? " [occupied]" : "");
	}

	public string ToString(int hashCode)
	{
		return ToString() + " score=" + get_Item(hashCode);
	}

	public void SetData(IntVector2 gridPos, int tileCost, bool isCandidate, bool isStartingNode, int movePower)
	{
		this.gridPos = gridPos;
		this.tileCost = tileCost;
		this.isCandidate = isCandidate || isStartingNode;
		this.movePower = isStartingNode ? movePower : movePower - tileCost;
		MarkUsed();
	}

	// [RE] Dijkstra edge relaxation: true if stepping from `from` improved us.
	public bool Calculate(TileNode from)
	{
		if (from == null || impassableCheck(from))
			return false;
		int newPower = from.movePower - tileCost;
		if (newPower <= movePower)
			return false;
		movePower = newPower;
		isCandidate = true;
		return true;
	}

	private static bool impassableCheck(TileNode from)
	{
		return from == null || from.movePower <= 0;
	}

	// [RE] path reconstruction helper: which neighbour continues toward dest
	public Adjacent GetNextPath(IntVector2 dest, Adjacent lastDirection)
	{
		int dx = dest.x - gridPos.x;
		int dy = dest.y - gridPos.y;
		if (dx == 0 && dy == 0)
			return Adjacent.start;
		if (System.Math.Abs(dx) >= System.Math.Abs(dy))
			return dx > 0 ? Adjacent.right : Adjacent.left;
		return dy > 0 ? Adjacent.top : Adjacent.down;
	}

	private Adjacent IsAdjacent(TileNode from)
	{
		if (from.gridPos.y == gridPos.y)
			return from.gridPos.x == gridPos.x + 1 ? Adjacent.right :
				from.gridPos.x == gridPos.x - 1 ? Adjacent.left : Adjacent.start;
		if (from.gridPos.x == gridPos.x)
			return from.gridPos.y == gridPos.y + 1 ? Adjacent.top :
				from.gridPos.y == gridPos.y - 1 ? Adjacent.down : Adjacent.start;
		return Adjacent.start;
	}

	// [RE] Thingsoft.Pool discipline: nodes are pooled per battle.
	public static TileNode Allocate()
	{
		if (ms_pool.Count > 0)
		{
			var n = ms_pool.Dequeue();
			n.restored = 0;
			n.Clear();
			return n;
		}
		return new TileNode();
	}

	public static void Restore(TileNode one)
	{
		if (one != null)
		{
			one.Clear();
			ms_pool.Enqueue(one);
		}
	}

	public bool isRestored()
	{
		return restored != 0;
	}

	public void MarkRestored()
	{
		restored = 1;
	}

	public void MarkUsed()
	{
		restored = 0;
	}
}
