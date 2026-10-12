// [RE] Rebuilt AStarPathFinder — the ORIGINAL SRPG pathfinder class
// (stub preserved every member: AStarNode{row,column,h,g,f,priority,cost},
// ByFCost IComparer, List openList + HashSet closedList, loopLimit guard,
// GetAroundPassablePosition blocked-destination fallback).
// Design notes + the diff-vs-canonical-A* exercise: docs/lab/dsa/03.
using System.Collections.Generic;
using System.Runtime.InteropServices;

public class AStarPathFinder
{
	[StructLayout((LayoutKind)0, Size = 12)]
	public struct ArrayPosition
	{
		public int row;

		public int col;

		public int cost;

		public ArrayPosition(int row, int col, int cost)
		{
			this.row = row;
			this.col = col;
			this.cost = cost;
		}
	}

	public class AStarNode
	{
		public bool impassable;

		public AStarNode parentNode;

		public int row;

		public int column;

		public int h;

		public int g;

		public int f;

		public ulong priority;

		public int cost;
	}

	public class ByFCost : IComparer<AStarNode>
	{
		public int Compare(AStarNode x, AStarNode y)
		{
			// [RE] f then priority (original ulong tiebreaker) then h —
			// ordering by (f, h) yields straighter paths from priority alone.
			if (x.f != y.f)
				return x.f.CompareTo(y.f);
			if (x.priority != y.priority)
				return x.priority.CompareTo(y.priority);
			return x.h.CompareTo(y.h);
		}
	}

	private AStarNode[,] map;

	private int mapRows;

	private int mapColumns;

	private List<AStarNode> openList;

	private HashSet<AStarNode> closedList;

	private ByFCost comparer;

	public AStarPathFinder()
	{
		openList = new List<AStarNode>();
		closedList = new HashSet<AStarNode>();
		comparer = new ByFCost();
	}

	public void SetMap(AStarNode[,] map, int rows, int columns)
	{
		this.map = map;
		mapRows = rows;
		mapColumns = columns;
		openList.Clear();
		closedList.Clear();
	}

	public bool IsPassable(int row, int col)
	{
		if (row < 0 || col < 0 || row >= mapRows || col >= mapColumns)
			return false;
		AStarNode node = map[row, col];
		return node != null && !node.impassable;
	}

	private void CalculateG(AStarNode calculatingNode)
	{
		calculatingNode.g = GetG(calculatingNode, calculatingNode.parentNode);
	}

	// [RE] step cost = destination tile cost (terrain); parent chains the sum.
	private int GetG(AStarNode calculatingNode, AStarNode parentNode)
	{
		if (parentNode == null)
			return 0;
		return parentNode.g + calculatingNode.cost;
	}

	private void CalculateH(AStarNode calculatingNode, AStarNode destNode)
	{
		// [RE] Manhattan — admissible + consistent on 4-connected grid (dsa-03 §2)
		int dr = calculatingNode.row > destNode.row ? calculatingNode.row - destNode.row : destNode.row - calculatingNode.row;
		int dc = calculatingNode.column > destNode.column ? calculatingNode.column - destNode.column : destNode.column - calculatingNode.column;
		int minCost = int.MaxValue;
		for (int r = 0; r < mapRows; r++)
			for (int c = 0; c < mapColumns; c++)
			{
				AStarNode n = map[r, c];
				if (n != null && !n.impassable && n.cost < minCost)
					minCost = n.cost;
			}
		if (minCost == int.MaxValue)
			minCost = 1;
		calculatingNode.h = (dr + dc) * minCost;
	}

	private void CalculateF(AStarNode calculatingNode)
	{
		calculatingNode.f = calculatingNode.g + calculatingNode.h;
	}

	private void CalculatePriority(AStarNode calculatingNode)
	{
		// [RE] original ulong priority: high bits = f, low bits = h — a
		// two-tier comparison in one integer (dsa-01 bit-packing lesson).
		calculatingNode.priority = ((ulong)(uint)calculatingNode.f << 32) | (ulong)(uint)calculatingNode.h;
	}

	// [RE] blocked-destination fallback: nearest passable ring tile toward start
	private ArrayPosition GetAroundPassablePosition(ArrayPosition impassablePosition, ArrayPosition startPosition)
	{
		ArrayPosition best = impassablePosition;
		int bestH = int.MaxValue;
		for (int dr = -1; dr <= 1; dr++)
			for (int dc = -1; dc <= 1; dc++)
			{
				if (dr == 0 && dc == 0)
					continue;
				int r = impassablePosition.row + dr;
				int c = impassablePosition.col + dc;
				if (!IsPassable(r, c))
					continue;
				int h = (r > startPosition.row ? r - startPosition.row : startPosition.row - r)
					+ (c > startPosition.col ? c - startPosition.col : startPosition.col - c);
				if (h < bestH)
				{
					bestH = h;
					best = new ArrayPosition(r, c, map[r, c].cost);
				}
			}
		return best;
	}

	public Stack<ArrayPosition> FindPath(ArrayPosition startPosition, ArrayPosition destPosition, int loopLimit)
	{
		var result = new Stack<ArrayPosition>();
		if (map == null)
			return result;
		if (!IsPassable(startPosition.row, startPosition.col))
			return result;
		if (!IsPassable(destPosition.row, destPosition.col))
		{
			// [RE] original fallback: reroute to the nearest passable neighbour
			ArrayPosition around = GetAroundPassablePosition(destPosition, startPosition);
			if (around.row == destPosition.row && around.col == destPosition.col)
				return result;
			destPosition = around;
		}

		openList.Clear();
		closedList.Clear();

		AStarNode startNode = map[startPosition.row, startPosition.col];
		AStarNode destNode = map[destPosition.row, destPosition.col];
		CalculateH(startNode, destNode);
		startNode.g = 0;
		startNode.parentNode = null;
		CalculateF(startNode);
		CalculatePriority(startNode);
		openList.Add(startNode);

		int loops = 0;
		bool found = false;
		while (openList.Count > 0)
		{
			if (loopLimit > 0 && ++loops > loopLimit)
				break;   // [RE] original safety valve against pathological maps

			openList.Sort(comparer);   // [RE] original used a List+IComparer, NOT a heap (dsa-03 §2)
			AStarNode current = openList[0];
			openList.RemoveAt(0);
			closedList.Add(current);

			if (current == destNode)
			{
				found = true;
				break;
			}

			for (int dr = -1; dr <= 1; dr++)
				for (int dc = -1; dc <= 1; dc++)
				{
					if ((dr == 0) == (dc == 0))
						continue;   // [RE] 4-connected (no diagonals in this SRPG)
					int r = current.row + dr;
					int c = current.column + dc;
					if (!IsPassable(r, c))
						continue;
					AStarNode neighbor = map[r, c];
					if (closedList.Contains(neighbor))
						continue;
					if (neighbor.parentNode == null || neighbor.g > current.g + neighbor.cost)
					{
						neighbor.parentNode = current;
						CalculateG(neighbor);
						CalculateH(neighbor, destNode);
						CalculateF(neighbor);
						CalculatePriority(neighbor);
						if (!openList.Contains(neighbor))
							openList.Add(neighbor);
					}
				}
		}

		if (!found)
			return result;
		AStarNode walker = destNode;
		while (walker != null && walker != startNode)
		{
			// [RE] Stack ordering: pop = step order from start→dest
			result.Push(new ArrayPosition(walker.row, walker.column, walker.cost));
			walker = walker.parentNode;
		}
		return result;
	}
}
