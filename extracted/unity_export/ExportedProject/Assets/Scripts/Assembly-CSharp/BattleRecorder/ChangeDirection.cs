using Caocao;

namespace BattleRecorder
{
	public sealed class ChangeDirection : Action
	{
		private readonly UnitObject m_unitObj;

		private readonly UnitDirection m_fromDir;

		private readonly UnitDirection m_toDir;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public ChangeDirection(UnitObject unitObj, UnitDirection fromDir, UnitDirection toDir)
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

		public static ChangeDirection Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
