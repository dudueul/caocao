namespace BattleRecorder
{
	public sealed class EventMenuOnChange : Action
	{
		private bool m_eventMenuOn;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public EventMenuOnChange(bool eventMenuOn)
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

		public static EventMenuOnChange Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
