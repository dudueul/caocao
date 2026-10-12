using System;
using LitJson;

namespace BattlePlay
{
	[Serializable]
	public class BattleTreasure : ITransmittable
	{
		public ulong srl;

		public int id;

		public int level;

		public int passive1;

		public int passive2;

		public int socketPassive;

		public int dbLevel;

		public int dbPassive1;

		public int dbPassive2;

		public BattleTreasure()
		{
		}

		public BattleTreasure(UserItem userItem, UserTreasureSocket userTreasureSocket)
		{
		}

		public BattleTreasure(JsonData jsonData)
		{
		}

		private void Init()
		{
		}

		public void SetTreasure(UserItem userItem, UserTreasureSocket userTreasureSocket)
		{
		}

		public UserItem GetUserItem()
		{
			return null;
		}

		public UserTreasureSocket GetUserTreasureSocket()
		{
			return null;
		}

		public override void Serialize(JsonWriter writer)
		{
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}
	}
}
