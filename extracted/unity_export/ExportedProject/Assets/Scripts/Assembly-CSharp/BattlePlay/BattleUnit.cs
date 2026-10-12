using System;
using LitJson;

namespace BattlePlay
{
	[Serializable]
	public class BattleUnit : ITransmittable
	{
		public ulong srl;

		public int unitId;

		public int exp;

		public int jobLevel;

		public int reinforceLevel;

		public int skinItemId;

		public int[] passives;

		public BattleItem[] items;

		public BattleTreasure[] treasures;

		public BattleRune[] runes;

		public GeneralStat trainStat;

		public TroopStat relationStat;

		public EnumData.Brawl_Unit_State brawlUnitState;

		private UserUnit userUnitData;

		public BattleUnit()
		{
		}

		public BattleUnit(UserUnit userUnit)
		{
		}

		public BattleUnit(JsonData jsonData)
		{
		}

		public void SetUnitData(UserUnit userUnit)
		{
		}

		public UserUnit GetUserUnit(ulong uid)
		{
			return null;
		}

		public bool IsQueueState()
		{
			return false;
		}

		private void MakeUserUnit(ulong uid)
		{
		}

		private void Init()
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}
	}
}
