using System.Xml;
using UnityEngine;

[ExecuteInEditMode]
public class MapObjectData : TileData
{
	public int m_objectId;

	private BattleMapObject m_parentGroup;

	public int m_initState;

	public int m_appearance;

	public EnumData.OccupyType m_occuType;

	public int m_levelCorrect;

	public bool m_isCastle;

	public bool m_isUnit;

	public int m_tileId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public IntVector2 m_GridPos
	{
		get
		{
			return default(IntVector2);
		}
		set
		{
		}
	}

	public BattleMapObject parentGroup
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int SortingOrder
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int GetOrder()
	{
		return 0;
	}

	public void SetData(int initState, int appearance, bool isUnit, int levelCorrect, EnumData.OccupyType occuType, bool isCastle)
	{
	}

	public new XmlElement ToXML(XmlDocument xmlDoc)
	{
		return null;
	}

	private void OnDestroy()
	{
	}
}
