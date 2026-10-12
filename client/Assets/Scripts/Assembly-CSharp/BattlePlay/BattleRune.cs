using System;
using LitJson;

namespace BattlePlay
{
	[Serializable]
	public class BattleRune : ITransmittable
	{
		public int id;

		public int passive;

		public int exp;

		public int dbLevel;

		public bool isRestricted;

		public BattleRune()
		{
		}

		public BattleRune(RuneItemSlot runeItemSlot)
		{
		}

		public BattleRune(JsonData jsonData)
		{
		}

		public void SetRune(RuneItemSlot runeItemSlot)
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}

		protected override void DeserializeImpl(JsonData runeData)
		{
		}

		private void Init()
		{
		}
	}
}
