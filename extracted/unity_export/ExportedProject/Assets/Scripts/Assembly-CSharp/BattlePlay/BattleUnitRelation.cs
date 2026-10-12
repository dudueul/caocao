using System;
using LitJson;

namespace BattlePlay
{
	[Serializable]
	public class BattleUnitRelation : ITransmittable
	{
		public int id;

		public BattleUnitRelation()
		{
		}

		public BattleUnitRelation(JsonData jsonData)
		{
		}

		public BattleUnitRelation(UserUnitRelation unitRelation)
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}

		private void Init()
		{
		}
	}
}
