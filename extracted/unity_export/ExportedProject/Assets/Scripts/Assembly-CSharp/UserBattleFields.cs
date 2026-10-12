using System;
using System.Collections.Generic;
using LitJson;

public class UserBattleFields : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserBattleField> m_pdId2Field;

	private List<int> m_pdIds;

	private List<int> m_ignoreUnitList;

	public Dictionary<int, UserBattleField> dic
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

	public string SerializeToInfo()
	{
		return null;
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public UserBattleField GetUserBattleField(int fieldId)
	{
		return null;
	}

	public List<UserBattleField> GetAllUserBattleFields()
	{
		return null;
	}

	public void GetAutoDefenseDeck(int battleFieldId, List<object> args)
	{
	}

	private List<BattleFieldNpcGroupType> GetBFNpcGroupType(int battleFieldId)
	{
		return null;
	}

	private void SetAutoDefenseDeck(List<object> saveObjs, int groupNum, int bfGroup, int bfId, int tileId)
	{
	}

	private void WriteAutoDefensePosUnit(JsonWriter writer, int unitPos, int unitType, int tileId)
	{
	}

	public UserUnit GetBattleFieltUnitType(List<UserUnit> listUserUnit, EnumData.BattleFieldUnitType bfUnitType, int tileId, bool checkIgnoreUnitList)
	{
		return null;
	}
}
