using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserBattleField : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredInt m_fieldId;

	private ObscuredInt m_index;

	private ObscuredULong m_ownerId;

	private DateTime m_ownerChangeDate;

	private ObscuredULong m_searchUserId;

	private DateTime m_searchAvailableDate;

	private DateTime m_searchDate;

	private DateTime m_safeguardDate;

	private ObscuredInt m_battleCount;

	private ObscuredInt m_status;

	private DateTime m_searchExpireTime;

	public ulong Srl
	{
		get
		{
			return 0uL;
		}
	}

	public int FieldId
	{
		get
		{
			return 0;
		}
	}

	public int Index
	{
		get
		{
			return 0;
		}
	}

	public ulong OwnerId
	{
		get
		{
			return 0uL;
		}
	}

	public DateTime OwnerChangeDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public ulong SearchUserId
	{
		get
		{
			return 0uL;
		}
	}

	public DateTime SearchAvailableDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public int BattleCount
	{
		get
		{
			return 0;
		}
	}

	public int Status
	{
		get
		{
			return 0;
		}
	}

	public bool IsSearching
	{
		get
		{
			return false;
		}
	}

	public bool IsSafeguard
	{
		get
		{
			return false;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void GetRewardValue(int productionValue, out int nowValue, out int maxValue, bool isLoot = false)
	{
		nowValue = default(int);
		maxValue = default(int);
	}

	public int GetLootBonusValue(int productionType)
	{
		return 0;
	}
}
