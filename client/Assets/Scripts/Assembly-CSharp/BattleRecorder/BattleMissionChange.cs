namespace BattleRecorder
{
	public sealed class BattleMissionChange : Action
	{
		private int missionId;

		private BattleMissionState prev;

		private BattleMissionState cur;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public BattleMissionChange(BattleMission mission)
		{
		}

		private BattleMissionChange()
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

		public static BattleMissionChange Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
