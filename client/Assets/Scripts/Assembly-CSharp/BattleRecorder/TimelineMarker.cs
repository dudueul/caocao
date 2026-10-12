namespace BattleRecorder
{
	public sealed class TimelineMarker : Action
	{
		private bool m_eventMenuOn;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public TimelineMarker(bool eventMenuOn)
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

		public static TimelineMarker Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
