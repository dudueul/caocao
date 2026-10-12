using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserArenaShopItem : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_index;

	private ObscuredInt m_saleType;

	private ObscuredInt m_saleId;

	private ObscuredString m_saleDetail;

	private ObscuredInt m_cost;

	private ObscuredInt m_purchased;

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

	public int index
	{
		get
		{
			return 0;
		}
	}

	public EnumData.ArenaPresetType saleType
	{
		get
		{
			return (EnumData.ArenaPresetType)0;
		}
	}

	public int saleId
	{
		get
		{
			return 0;
		}
	}

	public int cost
	{
		get
		{
			return 0;
		}
	}

	public bool purchased
	{
		get
		{
			return false;
		}
	}

	public Action deserializeCallback { get; set; }

	public JsonData saleDetail { get; private set; }

	public int unitId
	{
		get
		{
			return 0;
		}
	}

	public int weaponId
	{
		get
		{
			return 0;
		}
	}

	public int armorId
	{
		get
		{
			return 0;
		}
	}

	public int kitId
	{
		get
		{
			return 0;
		}
	}

	public JsonData passives
	{
		get
		{
			return null;
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
