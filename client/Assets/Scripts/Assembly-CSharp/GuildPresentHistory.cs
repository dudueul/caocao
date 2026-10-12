using System;
using LitJson;

public class GuildPresentHistory : ITransmittable
{
	public ulong userId;

	public ulong guildId;

	public EnumData.GoodsType goodsType;

	public int itemId;

	public int deltaAmount;

	public int afterAmount;

	public DateTime regDate;

	public string nationName;

	public bool isPresent
	{
		get
		{
			return false;
		}
	}

	public int amount
	{
		get
		{
			return 0;
		}
	}

	public GuildPresentHistory()
	{
	}

	public GuildPresentHistory(JsonData jsonData)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
