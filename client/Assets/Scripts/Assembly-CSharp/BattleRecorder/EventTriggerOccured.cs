namespace BattleRecorder
{
	public sealed class EventTriggerOccured : Action
	{
		private int m_index;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public EventTriggerOccured(int index)
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

		public static EventTriggerOccured Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
