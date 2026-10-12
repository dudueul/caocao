using System.Collections.Generic;
using UnityEngine;

public class UnitMetaData
{
	public class RangeShape
	{
		private int[,] shape;

		private int[,] proxy;

		public int Length
		{
			get
			{
				return 0;
			}
		}

		// C# has no syntax for parameterized property 'Item'.
		public int get_Item(int y, int x)
		{
			return 0;
		}

		public void set_Item(int y, int x, int value)
		{
		}

		public IntVector2 dimension { get; private set; }

		public bool hasProxy
		{
			get
			{
				return false;
			}
		}

		public RangeShape(int[,] shape)
		{
		}

		public int GetLength(int dimension)
		{
			return 0;
		}

		public void _SetProxy(int[,] proxy)
		{
		}

		public static RangeShape merge(RangeShape l, RangeShape r)
		{
			return null;
		}
	}

	private static Dictionary<int, RangeShape> type2AttackRange;

	private static Dictionary<int, RangeShape> type2EffectRange;

	private static Dictionary<int, RangeShape> type2AdditionalAttackRange;

	private static RangeShape shortDistanceArcheryShape;

	private static EffectGridSorter m_effectGridOrderCaculator;

	static UnitMetaData()
	{
	}

	public static int GetUnitMoveRange(IntVector2 baseGridPos, int moveRadius, List<IntVector2> outMovableGrids)
	{
		return 0;
	}

	public static RangeShape GetAttackAreaShape(int attackRangeType, bool expendAttackRange, bool hasShortDistanceArchery)
	{
		return null;
	}

	public static RangeShape GetExpansionAttackAreaShape(int attackRangeType)
	{
		return null;
	}

	private static int GetAreaState(Dictionary<int, RangeShape> rangeShape, int rangeType, int x, int y, IntVector2 mapDimension)
	{
		return 0;
	}

	public static RangeShape GetEffectAreaShape(int effectRangeType, GameObject assultUnitObj, GameObject assultedUnitObj)
	{
		return null;
	}

	public static List<IntVector2> GetEffectAreaGrid(int effectRangeType, RangeShape effectArea, GameObject assultedUnitObj, GameObject triggerUnitObj = null, bool forcedSortByRule = false)
	{
		return null;
	}

	public static List<IntVector2> GetDirectionalGrids(int distance, GameObject assultUnitObj, GameObject assultedUnitObj, bool fromStart = false)
	{
		return null;
	}

	public static IntVector2 SearchEmptyGrid(IntVector2 baseGridPos)
	{
		return default(IntVector2);
	}

	public static IntVector2 GetPushAndPullDestGridPos(UnitData assultUnitData, UnitData assultedUnitData, bool fromStart = false)
	{
		return default(IntVector2);
	}
}
