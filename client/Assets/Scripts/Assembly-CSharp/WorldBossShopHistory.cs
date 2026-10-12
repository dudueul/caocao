using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class WorldBossShopHistory : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredString m_yyyymmdd;

	private ObscuredInt m_shopId;

	private ObscuredInt m_purchaseCount;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public string yyyymmdd
	{
		get
		{
			return null;
		}
	}

	public int shopId
	{
		get
		{
			return 0;
		}
	}

	public int purchaseCount
	{
		get
		{
			return 0;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
