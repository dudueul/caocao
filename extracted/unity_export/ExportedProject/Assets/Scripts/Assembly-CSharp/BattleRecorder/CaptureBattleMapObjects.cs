using Caocao;

namespace BattleRecorder
{
	public sealed class CaptureBattleMapObjects : Action
	{
		private readonly UnitObject m_unitObj;

		private UnitSide m_prevSide;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		public CaptureBattleMapObjects(UnitObject unitObj, UnitSide prevSide)
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

		public static CaptureBattleMapObjects Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
