using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

namespace Chat
{
	public class ChatBlockUser
	{
		private ObscuredULong m_uid;

		private ObscuredString m_nationName;

		private DateTime m_regDate;

		public ulong userId
		{
			get
			{
				return 0uL;
			}
		}

		public string nationName
		{
			get
			{
				return null;
			}
		}

		public void Deserialize(JsonData jsonData)
		{
		}
	}
}
