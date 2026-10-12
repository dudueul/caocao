using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

namespace Multiplay
{
	public class Replay : ITransmittable
	{
		private ObscuredULong m_srl;

		private ObscuredString m_battleKey;

		private ObscuredULong m_uid;

		private ObscuredInt m_tierGrade;

		private ObscuredInt m_point;

		private ObscuredULong m_opponentId;

		private ObscuredInt m_opponentTierGrade;

		private ObscuredInt m_opponentPoint;

		private ObscuredInt m_revision;

		private ObscuredEnum<EnumData.UserInfoOpenRange> m_openRange;

		private ObscuredInt m_recommendCount;

		private DateTime m_regDate;

		private ObscuredInt m_battleId;

		public ulong srl
		{
			get
			{
				return 0uL;
			}
			set
			{
			}
		}

		public string battleKey
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public ulong uid
		{
			get
			{
				return 0uL;
			}
			set
			{
			}
		}

		public int tierGrade
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public int point
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public ulong opponentId
		{
			get
			{
				return 0uL;
			}
			set
			{
			}
		}

		public int opponentTierGrade
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public int opponentPoint
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public int revision
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public EnumData.UserInfoOpenRange openRange
		{
			get
			{
				return EnumData.UserInfoOpenRange.All;
			}
			set
			{
			}
		}

		public int recommendCount
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public DateTime regDate
		{
			get
			{
				return default(DateTime);
			}
			set
			{
			}
		}

		public int battleId
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public TimeSpan timeBefore
		{
			get
			{
				return default(TimeSpan);
			}
		}

		public Replay()
		{
		}

		public Replay(JsonData replayData)
		{
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}

		private void ParseBattleMapInfo()
		{
		}

		public static int SortRecommend(Replay x, Replay y)
		{
			return 0;
		}

		public static int Sort(Replay x, Replay y)
		{
			return 0;
		}
	}
}
