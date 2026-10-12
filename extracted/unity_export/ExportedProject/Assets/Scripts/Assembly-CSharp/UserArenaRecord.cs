using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserArenaRecord : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_clearLevel;

	private ObscuredInt m_stamp;

	private ObscuredString m_unitGroup;

	private DateTime m_battleDate;

	private ObscuredInt m_isLock;

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

	public int clearLevel
	{
		get
		{
			return 0;
		}
	}

	public int stamp
	{
		get
		{
			return 0;
		}
	}

	public JsonData unitGroup { get; private set; }

	public DateTime battleDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public bool isLock
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
}
