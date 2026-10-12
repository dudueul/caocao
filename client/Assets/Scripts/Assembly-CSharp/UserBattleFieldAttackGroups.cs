using System;
using System.Collections.Generic;
using LitJson;

public class UserBattleFieldAttackGroups : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserBattleFieldAttackGroup> m_srl2Group;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserBattleFieldAttackGroup> dic
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

	public List<UserBattleFieldAttackGroup> GetAllAttackGroups()
	{
		return null;
	}

	public UserBattleFieldAttackGroup GetAttackGroup(int fieldId)
	{
		return null;
	}

	public List<int> GetAllAttackGroupUnitId()
	{
		return null;
	}

	public bool IsAttackGroup(int unitId)
	{
		return false;
	}

	public int GetAttackFieldId(int unitId)
	{
		return 0;
	}

	public bool IsAttackField(int fieldId)
	{
		return false;
	}
}
