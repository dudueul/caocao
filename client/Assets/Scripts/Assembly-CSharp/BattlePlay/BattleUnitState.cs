using System;
using System.Collections.Generic;
using LitJson;

namespace BattlePlay
{
	[Serializable]
	public class BattleUnitState : ITransmittable
	{
		public ulong userId;

		public ulong srl;

		public int unitId;

		public int hp;

		public int mp;

		public int ep;

		public List<int> conditions;

		public List<int> passives;

		public BattleUnitState()
		{
		}

		public BattleUnitState(UnitData unitData)
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}
	}
}
