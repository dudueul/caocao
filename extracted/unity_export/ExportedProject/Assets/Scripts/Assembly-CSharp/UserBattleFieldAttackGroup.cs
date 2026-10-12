using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserBattleFieldAttackGroup : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredInt m_fieldId;

	private List<int> m_units;

	private DateTime m_UpdateDate;

	public ulong Srl
	{
		get
		{
			return 0uL;
		}
	}

	public int FieldID
	{
		get
		{
			return 0;
		}
	}

	public DateTime UpdateDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public List<int> Units
	{
		get
		{
			return null;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsAttackField()
	{
		return false;
	}
}
