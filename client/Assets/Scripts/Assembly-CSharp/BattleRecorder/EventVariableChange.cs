namespace BattleRecorder
{
	public sealed class EventVariableChange : Action
	{
		private int m_index;

		private int m_value;

		private int m_prevValue;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public EventVariableChange(int index, int value, int prevValue)
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

		public static EventVariableChange Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
