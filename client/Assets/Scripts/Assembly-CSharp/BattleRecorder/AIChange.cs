namespace BattleRecorder
{
	public sealed class AIChange : Action
	{
		private UnitObject m_unitObj;

		private int m_prevAIType;

		private TargetInfo m_prevTargetUnitInfo;

		private IntVector2 m_prevTargetPos;

		private int m_aiType;

		private TargetInfo m_targetUnitInfo;

		private IntVector2 m_targetPos;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		private AIChange()
		{
		}

		public AIChange(UnitData unitData, int aiType, TargetInfo targetUnitInfo, IntVector2 targetPos)
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

		public static AIChange Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
