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

	public bool isMovableTile
	{
		get
		{
			return false;
		}
	}

	public int movePower { get; private set; }

	public int ignoreZocMovePower { get; set; }

	public int pdTileCost { get; set; }

	// C# has no syntax for parameterized property 'Item'.
	public float get_Item(int hashCode)
	{
		return 0f;
	}

	public void set_Item(int hashCode, float value)
	{
	}

	// C# has no syntax for parameterized property 'Item'.
	public int get_Item(Adjacent adjType)
	{
		return 0;
	}

	private void set_Item(Adjacent adjType, int value)
	{
	}

	private TileNode()
	{
	}

	public override bool Equals(object obj)
	{
		return false;
	}

	public override int GetHashCode()
	{
		return 0;
	}

	public override string ToString()
	{
		return null;
	}

	public string ToString(int hashCode)
	{
		return null;
	}

	public void SetData(IntVector2 gridPos, int tileCost, bool isCandidate, bool isStartingNode, int movePower)
	{
	}

	public bool Calculate(TileNode from)
	{
		return false;
	}

	private Adjacent IsAdjacent(TileNode from)
	{
		return Adjacent.start;
	}

	public Adjacent GetNextPath(IntVector2 dest, Adjacent lastDirection)
	{
		return Adjacent.start;
	}

	public static TileNode Allocate()
	{
		return null;
	}

	public static void Restore(TileNode one)
	{
	}

	public bool isRestored()
	{
		return false;
	}

	public void MarkRestored()
	{
	}

	public void MarkUsed()
	{
	}
}
