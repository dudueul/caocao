using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

namespace Multiplay
{
	public class MultiplayUserData : ITransmittable
	{
		private ObscuredULong m_userId;

		private ObscuredString m_authId;

		private ObscuredInt m_kingUnit;

		private ObscuredString m_nationName;

		private ObscuredInt m_faceUnitId;

		private ObscuredInt m_skinItemId;

		private ObscuredInt m_guildId;

		private ObscuredString m_guildName;

		private JsonData m_emblem;

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

		public string authId
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public int kingUnit
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public string nationName
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public int faceUnitId
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public int skinItemId
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public int guildId
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public string guildName
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public JsonData emblem
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public MultiplayUserData()
		{
		}

		public MultiplayUserData(JsonData replayData)
		{
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}
	}
}
