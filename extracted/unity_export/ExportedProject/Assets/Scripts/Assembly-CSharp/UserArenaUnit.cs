using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserArenaUnit : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_unitId;

	private ObscuredInt m_presetId;

	private ObscuredULong m_weapon;

	private ObscuredULong m_armor;

	private ObscuredULong m_kit;

	private ObscuredULong m_passive1;

	private ObscuredULong m_passive2;

	private ObscuredULong m_passive3;

	private ObscuredULong m_passive4;

	private ObscuredULong m_passive5;

	private ObscuredInt m_deleted;

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

	public int unitId
	{
		get
		{
			return 0;
		}
	}

	public int presetId
	{
		get
		{
			return 0;
		}
	}

	public ulong weapon
	{
		get
		{
			return 0uL;
		}
	}

	public ulong armor
	{
		get
		{
			return 0uL;
		}
	}

	public ulong kit
	{
		get
		{
			return 0uL;
		}
	}

	public ulong passive1
	{
		get
		{
			return 0uL;
		}
	}

	public ulong passive2
	{
		get
		{
			return 0uL;
		}
	}

	public ulong passive3
	{
		get
		{
			return 0uL;
		}
	}

	public ulong passive4
	{
		get
		{
			return 0uL;
		}
	}

	public ulong passive5
	{
		get
		{
			return 0uL;
		}
	}

	public ulong[] passives { get; private set; }

	public bool deleted
	{
		get
		{
			return false;
		}
	}

	public Action deserializeCallback { get; set; }

	public Action upsertCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void Reset()
	{
	}
}
