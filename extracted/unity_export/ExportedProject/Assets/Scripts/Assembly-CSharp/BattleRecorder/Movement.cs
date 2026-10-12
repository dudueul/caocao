using Caocao;

namespace BattleRecorder
{
	public sealed class Movement : Action
	{
		private readonly UnitObject m_unitObj;

		private readonly IntVector2 m_fromPos;

		private readonly UnitDirection m_fromDir;

		private readonly IntVector2 m_toPos;

		private readonly UnitDirection m_toDir;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public Movement(UnitObject unitObj, IntVector2 fromPos, UnitDirection fromDir, IntVector2 toPos, UnitDirection toDir)
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

		public static Movement Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
