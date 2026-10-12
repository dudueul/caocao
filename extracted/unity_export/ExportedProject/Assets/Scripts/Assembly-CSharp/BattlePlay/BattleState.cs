using System;
using System.Collections.Generic;
using LitJson;

namespace BattlePlay
{
	[Serializable]
	public class BattleState : ITransmittable
	{
		public ulong uid;

		public string battleKey;

		public int turn;

		public int turnIdx;

		public int turnUnitIdx;

		public List<BattleUnitState> turnOrder;

		public ControlFlow.statusType controlStatus;

		public int controlCount;

		public BattleControl control;

		public int dead;

		public int alive;

		public int enemyDead;

		public int enemyAlive;

		public BattleState(string battleKey, BattlePlayData battlePlayData)
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}
	}
}
