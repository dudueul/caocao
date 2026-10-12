using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserArenaTreasure : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_treasureId;

	private ObscuredInt m_lock;

	private ObscuredInt m_ownerUnit;

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

	public int treasureId
	{
		get
		{
			return 0;
		}
	}

	public EnumData.ArenaLockState lockState
	{
		get
		{
			return EnumData.ArenaLockState.Unlock;
		}
	}

	public int ownerUnit
	{
		get
		{
			return 0;
		}
	}

	public bool deleted
	{
		get
		{
			return false;
		}
	}

	public Action deserializeCallback { get; set; }

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
