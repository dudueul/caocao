using System;
using LitJson;

namespace BattlePlay
{
	[Serializable]
	public class BattleItem : ITransmittable
	{
		public int id;

		public int exp;

		public BattleItem()
		{
		}

		public BattleItem(UserItem userItem)
		{
		}

		public BattleItem(JsonData jsonData)
		{
		}

		private void Init()
		{
		}

		public void SetItem(UserItem userItem)
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}

		protected override void DeserializeImpl(JsonData runeData)
		{
		}
	}
}
