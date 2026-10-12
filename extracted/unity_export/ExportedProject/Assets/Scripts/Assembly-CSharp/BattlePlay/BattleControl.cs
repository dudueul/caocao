using System;
using LitJson;

namespace BattlePlay
{
	[Serializable]
	public class BattleControl : ITransmittable
	{
		public ulong uid;

		public ControlType type;

		public int controlIdx;

		public int randomIdx;

		public ControlFlow.statusType status;

		public EnumData.CampLocation controlUnitSlot;

		public int controlUnitId;

		public EnumData.CampLocation targetUnitSlot;

		public int targetUnitId;

		public int tileIdx;

		public int skillId;

		public int itemId;

		public IntVector2 mouseGridPos;

		public int turnIdx;

		public int remainedSec;

		public bool applied;

		private string m_controlUnitName;

		private string m_targetUnitName;

		public BattleControl()
		{
		}

		public BattleControl(ulong uid, ControlType type, int controlIdx, int randomIdx, ControlFlow.statusType status, UnitData controlUnit, UnitData targetUnit, int tileIdx, int skillId, int itemId, IntVector2 mouseGridPos, int turnIdx, int remainedSec)
		{
		}

		public BattleControl(JsonData data)
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}

		protected virtual void EndWriteObject(JsonWriter writer)
		{
		}

		protected override void DeserializeImpl(JsonData data)
		{
		}

		public void Log(string head)
		{
		}
	}
}
