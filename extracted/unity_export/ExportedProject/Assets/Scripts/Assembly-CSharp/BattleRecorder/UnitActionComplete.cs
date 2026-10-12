namespace BattleRecorder
{
	public sealed class UnitActionComplete : Action
	{
		private UnitObject m_unitObj;

		private bool m_actionCompleted;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public UnitActionComplete(UnitObject actor, bool actionCompleted)
		{
		}

		public override void _Apply()
		{
		}

		public override void _Revert()
		{
		}

		public UnitData GetActor()
		{
			return null;
		}

		public override bool NeedToRecord()
		{
			return false;
		}

		public static UnitActionComplete Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
