using LitJson;

public class GuildStoreHistory : ITransmittable
{
	public int srl;

	public ulong uid;

	public int shopId;

	public int amount;

	public int yyyymm;

	public GuildStoreHistory(JsonData jsonData)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
