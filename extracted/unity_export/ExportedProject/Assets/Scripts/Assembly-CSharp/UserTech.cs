using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserTech : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_uid;

	private ObscuredInt m_techId;

	private ObscuredInt m_level;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public ulong uid
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public int techId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int level
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
