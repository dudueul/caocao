using System.Collections.Generic;
using UnityEngine;

public class EffectGridSorter
{
	private bool m_forceByRule;

	private int m_effectRangeType;

	private UnitMetaData.RangeShape m_effectArea;

	private List<IntVector2> m_effectAreaGrid;

	private int m_effectAreaLengthX;

	private int m_effectAreaLengthY;

	private int m_effectAreaBasePosX;

	private int m_effectAreaBasePosY;

	private int m_baseTargetUnitGridPosX;

	private int m_baseTargetUnitGridPosY;

	private int m_mapSizeX;

	private int m_mapSizeY;

	private bool triggerUnit;

	private int triggerUnitGridPosX;

	private int triggerUnitGridPosY;

	public bool ForceByRule
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public List<IntVector2> sortedEffectsGrid
	{
		get
		{
			return null;
		}
	}

	public void Setup(int effectRangeType, UnitMetaData.RangeShape effectArea, List<IntVector2> effectAreaGrid, GameObject assultedUnitObj, int mapSizeX, int mapSizeY, GameObject triggerUnitObj)
	{
	}

	public void AddOrderedGrid(int gridPosIteratedX, int gridPosIteratedY)
	{
	}

	public void AddWheelsEffectGrid()
	{
	}

	public void Sort()
	{
	}

	private void SortByIdx()
	{
	}

	private void SortByRule()
	{
	}
}
