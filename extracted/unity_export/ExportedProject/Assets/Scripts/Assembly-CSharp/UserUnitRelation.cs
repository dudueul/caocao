using System;
using System.Collections.Generic;
using BattlePlay;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserUnitRelation : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_relationId;

	private ObscuredInt m_status;

	private ObscuredBool m_favorite;

	private ulong ms_srl;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong uid
	{
		get
		{
			return 0uL;
		}
	}

	public int relationId
	{
		get
		{
			return 0;
		}
	}

	public EnumData.UnitRelationStatus status
	{
		get
		{
			return EnumData.UnitRelationStatus.NotPurchased;
		}
	}

	public bool favorite
	{
		get
		{
			return false;
		}
	}

	public Action deserializeCallback { get; set; }

	public bool appliedStat { get; set; }

	public UserUnitRelation()
	{
	}

	public UserUnitRelation(ulong userId, int relationId, int status)
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsUsableInMultiplay(List<BattleUnit> battleUnits)
	{
		return false;
	}
}
