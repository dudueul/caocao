namespace BattleRecorder
{
	public sealed class EventSceneClosed : Action
	{
		private int m_index;

		private bool m_closed;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public EventSceneClosed(int index, bool closed)
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

		public static EventSceneClosed Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
