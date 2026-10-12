using LitJson;

public class BasketItem : ITransmittable
{
	private static int DEFAULT_MAX_COUNT;

	protected UserItem m_userItem;

	protected ItemUI m_itemUI;

	protected int m_count;

	public Item item { get; protected set; }

	public UserItem userItem
	{
		get
		{
			return null;
		}
		protected set
		{
		}
	}

	public virtual int id
	{
		get
		{
			return 0;
		}
	}

	public ItemUI itemUI
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int count
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public virtual int silver
	{
		get
		{
			return 0;
		}
	}

	public virtual int maxCount
	{
		get
		{
			return 0;
		}
	}

	public BasketItem()
	{
	}

	public BasketItem(ItemUI itemUI)
	{
	}

	public BasketItem(Item item)
	{
	}

	public void Reset()
	{
	}

	protected void UpdateCount()
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}
}
