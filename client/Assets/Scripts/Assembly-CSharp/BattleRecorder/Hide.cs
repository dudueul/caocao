namespace BattleRecorder
{
	public sealed class Hide : Action
	{
		private UnitObject m_unitObj;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public Hide(UnitObject unitObj)
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

		public static Hide Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
