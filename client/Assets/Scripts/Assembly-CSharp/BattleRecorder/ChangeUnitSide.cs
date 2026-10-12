using Caocao;

namespace BattleRecorder
{
	public sealed class ChangeUnitSide : Action
	{
		private UnitObject m_unitObj;

		private UnitSide m_prevUnitSide;

		private UnitSide m_unitSide;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		private ChangeUnitSide()
		{
		}

		public ChangeUnitSide(UnitObject unitObj, UnitSide unitSide)
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

		public static ChangeUnitSide Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
