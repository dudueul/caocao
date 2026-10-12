using System;
using System.Collections.Generic;
using LitJson;

public class GuildItem : ITransmittable
{
	public enum GoodsTypeShortcut
	{
		Food = 102,
		FreeSilver = 109,
		FreeGold = 103,
		ChargedGold = 99,
		Gold = 116,
		Stamp = 115,
		Exploit = 101,
		Fame = 97,
		AnnihilationTicket = 107,
		VipPoint = 118,
		GuildPoint = 112,
		GuildMoney = 117
	}

	public int srl;

	public ulong guildId;

	public string goodsType;

	public int itemId;

	public int amount;

	public DateTime regDate;

	public DateTime updDate;

	private Dictionary<ulong, int> histories;

	public bool isGoods
	{
		get
		{
			return false;
		}
	}

	public GuildItem(JsonData jsonData)
	{
	}

	public int GetHistory(ulong uid)
	{
		return 0;
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void DeserializePresentHistory(JsonData jsonData)
	{
	}

	public static int ShortcutToGoodsType(string goodsType)
	{
		return 0;
	}
}
