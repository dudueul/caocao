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

	public int moveDistance { get; private set; }

	public void Init(UnitData unit, int movePower, bool eventMove = false)
	{
	}

	public void Clear()
	{
	}

	public TileNode SetTileNode(IntVector2 gridPos, int tileCost, bool isCandidate, bool isStartingPos)
	{
		return null;
	}

	public TileNode GetTileNode(IntVector2 pos)
	{
		return null;
	}

	public TileNode GetTileNode(int x, int y)
	{
		return null;
	}

	public void ApplyZoc(bool isSpIgnoringZoc)
	{
	}

	public void UpdateDebugInfo()
	{
	}

	public void ForEach(Action<TileNode> action)
	{
	}

	private void Caculate(TileNode from, TileNode to)
	{
	}

	public void CalculateCost(TileNode startingNode)
	{
	}

	public void CalculateCostNoPass(TileNode startingNode)
	{
	}

	public List<TileNode> FindTileNodePath(IntVector2 unitGridPos, IntVector2 toGridPos)
	{
		return null;
	}
}
