using System.Collections.Generic;

namespace BattleRecorder
{
	public sealed class EventCmdExecution : Action
	{
		private List<short> m_addr;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public EventCmdExecution(List<short> addr)
		{
		}

		public override void _Apply()
		{
		}

		protected override bool _ApplyOnLoading()
		{
			return false;
		}

		public override void _Revert()
		{
		}

		public override bool NeedToRecord()
		{
			return false;
		}

		public static EventCmdExecution Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
