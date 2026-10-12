using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserSaveFlag : ITransmittable
{
	private static PermanentData ms_pd;

	private ObscuredULong m_uid;

	private ObscuredInt m_flagId;

	private ObscuredInt m_saveValue;

	public ulong uid
	{
		get
		{
			return 0uL;
		}
	}

	public int flagId
	{
		get
		{
			return 0;
		}
	}

	public int saveValue
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	public UserSaveFlag()
	{
	}

	public UserSaveFlag(ulong uid, int flagID, int saveValue)
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
