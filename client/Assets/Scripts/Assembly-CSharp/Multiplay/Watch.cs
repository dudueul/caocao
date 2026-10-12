using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

namespace Multiplay
{
	public class Watch : ITransmittable
	{
		private ObscuredString m_roomId;

		private ObscuredULong m_userId;

		private ObscuredInt m_point;

		private ObscuredInt m_tierGrade;

		private ObscuredULong m_opponentId;

		private ObscuredInt m_opponentTierGrade;

		private ObscuredInt m_opponentPoint;

		private ObscuredEnum<EnumData.MultiplayUserState> m_state;

		private ObscuredInt m_battleId;

		private ObscuredInt m_memberCnt;

		private ObscuredBool m_allowWatch;

		private ObscuredString m_battleKey;

		private ObscuredInt m_popularity;

		private ObscuredInt m_recommended;

		private ObscuredInt m_revision;

		private DateTime m_battleStart;

		public string roomId
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public ulong userId
		{
			get
			{
				return 0uL;
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

		public EnumData.MultiplayUserState state
		{
			get
			{
				return EnumData.MultiplayUserState.Online;
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

		public int memberCnt
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public bool allowWatch
		{
			get
			{
				return false;
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

		public int popularity
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public int recommended
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

		public DateTime battleStart
		{
			get
			{
				return default(DateTime);
			}
			set
			{
			}
		}

		public TimeSpan deltaTime
		{
			get
			{
				return default(TimeSpan);
			}
		}

		public Watch()
		{
		}

		public Watch(JsonData watchData)
		{
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}

		private void ParseUserId()
		{
		}

		private void ParseBattleInfo()
		{
		}

		public static int MemberCnt(Watch x, Watch y)
		{
			return 0;
		}

		public static int Sort(Watch x, Watch y)
		{
			return 0;
		}

		public void DeserializeMatchingData(JsonData jsonData)
		{
		}
	}
}
