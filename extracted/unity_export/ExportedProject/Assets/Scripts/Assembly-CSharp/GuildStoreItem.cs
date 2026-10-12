using System;

public class GuildStoreItem : StoreItem
{
	public int shopId { get; protected set; }

	public int openGuildLv { get; protected set; }

	public int order { get; protected set; }

	public void SetStoreItemData(Item item, int shopId, int price, int amount, int openGradeLv, DateTime validDate, int order)
	{
	}

	public void SetAmount(int amount)
	{
	}

	public bool IsGuildLvLocked(out int requireLevel)
	{
		requireLevel = default(int);
		return false;
	}
}
