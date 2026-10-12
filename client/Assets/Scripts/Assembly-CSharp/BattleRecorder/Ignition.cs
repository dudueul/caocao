namespace BattleRecorder
{
	public sealed class Ignition : Action
	{
		private int m_id;

		private IntVector2 m_pos;

		private bool m_ignited;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public Ignition(int id, IntVector2 pos, bool ignited)
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

		public static Ignition Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
