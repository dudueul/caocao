using System;
using System.Xml;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

public class TileData : MonoBehaviour, IComparable
{
	[HideInInspector]
	public bool m_showTileJobCost;

	[HideInInspector]
	public int m_jobCost;

	[HideInInspector]
	public string m_name;

	public ObscuredInt m_id;

	private IntVector2 m_gridPos;

	public int gridX;

	public int gridY;

	public EnumData.NoPass noPassFlag;

	public IntVector2 gridPos
	{
		get
		{
			return default(IntVector2);
		}
	}

	int IComparable.CompareTo(object b)
	{
		return 0;
	}

	public XmlElement ToXML(XmlDocument xmlDoc)
	{
		return null;
	}

	private void OnDrawGizmos()
	{
	}

	public void SetTileJobCostVisibility(bool visibility)
	{
	}

	public void UpdateJobCostData(int cost, GUIStyle guiStyle)
	{
	}
}
