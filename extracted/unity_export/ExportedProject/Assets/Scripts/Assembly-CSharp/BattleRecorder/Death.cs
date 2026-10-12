namespace BattleRecorder
{
	public sealed class Death : Action
	{
		private UnitObject m_unitObj;

		private bool m_dead;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public Death(UnitObject unitObj, bool dead)
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

		public static Death Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
