using System;
using System.Collections.Generic;

public class StackTileData : IComparable
{
	private List<TileData> m_stack;

	public int tileId
	{
		get
		{
			return 0;
		}
	}

	public int gridX
	{
		get
		{
			return 0;
		}
	}

	public int gridY
	{
		get
		{
			return 0;
		}
	}

	public TileData tileData
	{
		get
		{
			return null;
		}
	}

	public void Push(TileData tile)
	{
	}

	public void Push(MapObjectData mapObject)
	{
	}

	private TileData Peek()
	{
		return null;
	}

	public BattleMapObject GetMapObjectById(int mapObjectId)
	{
		return null;
	}

	public List<BattleMapObject> GetMapObjects()
	{
		return null;
	}

	int IComparable.CompareTo(object b)
	{
		return 0;
	}
}
