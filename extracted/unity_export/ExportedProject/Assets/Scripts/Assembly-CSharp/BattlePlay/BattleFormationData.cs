using System;
using System.Collections.Generic;
using LitJson;

namespace BattlePlay
{
	[Serializable]
	public class BattleFormationData : ITransmittable
	{
		public int id;

		public int level;

		public Dictionary<int, int> formation;

		public BattleFormationData()
		{
		}

		public BattleFormationData(JsonData jsonData)
		{
		}

		public void SetBattleFormation(UserBattleFormation battleFormation)
		{
		}

		public int GetUnitIdByIndex(int index)
		{
			return 0;
		}

		public int GetIndexByUnitId(int unitId)
		{
			return 0;
		}

		public int GetEffectValue(EnumData.BattleFormationEffectType targetEffectType)
		{
			return 0;
		}

		public BattleFormationFunc GetBattleFormationFunc(int level)
		{
			return null;
		}

		public int Count()
		{
			return 0;
		}

		public void Clear()
		{
		}

		public void Add(int index, int unitId)
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
