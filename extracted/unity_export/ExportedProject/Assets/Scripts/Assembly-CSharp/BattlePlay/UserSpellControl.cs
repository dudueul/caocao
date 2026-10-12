using System.Collections.Generic;
using Caocao;
using LitJson;

namespace BattlePlay
{
	public class UserSpellControl : BattleControl
	{
		public List<UnitSide> targetUnitSides;

		public List<int> targetUnitIds;

		public UserSpellControl(ulong uid, ControlType type, int controlIdx, int randomIdx, ControlFlow.statusType status, List<UnitData> targetUnits, int tileIdx, int skillId, int itemId, IntVector2 mouseGridPos, int turnIdx, int remainedSec)
		{
		}

		public UserSpellControl(JsonData data)
		{
		}

		protected override void EndWriteObject(JsonWriter writer)
		{
		}

		protected override void DeserializeImpl(JsonData data)
		{
		}
	}
}
