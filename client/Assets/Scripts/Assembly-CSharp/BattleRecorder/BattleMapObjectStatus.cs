namespace BattleRecorder
{
	public sealed class BattleMapObjectStatus : Action
	{
		private int m_mapObjectId;

		private IntVector2 m_gridPos;

		private int m_prevStatusId;

		private int m_statusId;

		public override byte key
		{
			get
			{
				return 0;
			}
		}

		private BattleMapObjectStatus()
		{
		}

		public BattleMapObjectStatus(int mapObjectId, IntVector2 gridPos, int prevStatusId, int statusId)
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

		public static BattleMapObjectStatus Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public override void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
