using System;
using LitJson;

namespace BattlePlay
{
	[Serializable]
	public class BattleTech : ITransmittable
	{
		public int id;

		public int level;

		public BattleTech()
		{
		}

		public BattleTech(JsonData jsonData)
		{
		}

		public BattleTech(UserTech userTech)
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
