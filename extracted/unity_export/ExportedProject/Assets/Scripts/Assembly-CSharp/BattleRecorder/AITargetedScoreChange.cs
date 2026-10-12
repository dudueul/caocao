namespace BattleRecorder
{
	public sealed class AITargetedScoreChange : Action
	{
		private UnitObject m_unitObj;

		private int m_prevScore;

		private int m_score;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		private AITargetedScoreChange()
		{
		}

		public AITargetedScoreChange(UnitData unitData, int score)
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

		public static AITargetedScoreChange Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
