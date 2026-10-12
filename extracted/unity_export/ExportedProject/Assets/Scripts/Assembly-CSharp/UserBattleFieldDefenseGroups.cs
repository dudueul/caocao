using System;
using System.Collections.Generic;
using LitJson;

public class UserBattleFieldDefenseGroups : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserBattleFieldDefenseGroup> m_srl2Group;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserBattleFieldDefenseGroup> dicSrl
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public List<UserBattleFieldDefenseGroup> GetDefenseGroupsForField(int fieldId)
	{
		return null;
	}

	public UserBattleFieldDefenseGroup GetDefenseGroupForArea(int fieldId, int areaId)
	{
		return null;
	}

	public int GetDefenseGroupPower(IUserDataBase userInfo, int fieldId)
	{
		return 0;
	}

	public List<int> GetAllDefenseGroupUnitId()
	{
		return null;
	}

	public List<int> GetDefenseGroupUnitId(int fieldId)
	{
		return null;
	}

	public bool IsDefenseGroup(int unitId, int ignoredFieldId = 0)
	{
		return false;
	}

	public int GetDefenseFieldId(int unitId)
	{
		return 0;
	}

	public int GetDefenseSoldierDeckId(int fieldId)
	{
		return 0;
	}

	public int GetAssignedSoldierDeckCount(int deckId)
	{
		return 0;
	}

	public BattleFieldDefenseUnit GetDefenseUnitEquippedItem(ulong itemSrl)
	{
		return null;
	}

	public BattleFieldDefenseUnit GetDefenseServerUnit(int unitId, int ignoredFieldId = 0)
	{
		return null;
	}
}
