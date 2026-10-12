using LitJson;

public class BasketSellItem : BasketItem
{
	public override int id
	{
		get
		{
			return 0;
		}
	}

	public override int silver
	{
		get
		{
			return 0;
		}
	}

	public override int maxCount
	{
		get
		{
			return 0;
		}
	}

	public BasketSellItem()
	{
	}

	public BasketSellItem(UserItem userItem)
	{
	}

	public BasketSellItem(ItemUI itemUI)
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}
}
