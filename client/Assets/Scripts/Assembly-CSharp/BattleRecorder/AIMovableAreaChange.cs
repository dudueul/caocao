using System.Collections.Generic;

namespace BattleRecorder
{
	public sealed class AIMovableAreaChange : Action
	{
		private UnitObject m_unitObj;

		private int m_prevAreaType;

		private List<IntRect> m_prevCoords;

		private IntVector2 m_prevPivotPos;

		private int m_prevAttackArea;

		private TargetInfo m_prevPivotUnitInfo;

		private int m_areaType;

		private List<IntRect> m_coords;

		private IntVector2 m_pivotPos;

		private int m_attackArea;

		private TargetInfo m_pivotUnitInfo;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		private AIMovableAreaChange()
		{
		}

		public AIMovableAreaChange(UnitData unitData, int areaType, List<IntRect> coords, IntVector2 pivot, int attackArea, TargetInfo pivotUnitInfo)
		{
		}

		public override void _Apply()
		{
		}

		public override void _Revert()
		{
		}

		public override bool NeedToRecord()
		{
			return false;
		}

		public static AIMovableAreaChange Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
