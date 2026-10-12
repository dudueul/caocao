using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class DefenseDeckUnit : ITransmittable
{
	private ObscuredULong m_uid;

	private ObscuredULong m_srl;

	private ObscuredULong m_unitSrl;

	private ObscuredInt m_townID;

	private ObscuredInt m_unitID;

	private ObscuredInt m_BFInex;

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

	public ulong unitSrl
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public int townID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int unitID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int BFIndex
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
