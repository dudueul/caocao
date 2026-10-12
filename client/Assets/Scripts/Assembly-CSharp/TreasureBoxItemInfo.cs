using System;

public class TreasureBoxItemInfo
{
	private int storyStampTokenItemId;

	public BoxShop boxPd { get; private set; }

	public int id
	{
		get
		{
			return 0;
		}
	}

	public int groupId
	{
		get
		{
			return 0;
		}
	}

	public EnumData.TreasureBoxOption boxOption
	{
		get
		{
			return EnumData.TreasureBoxOption.Normal;
		}
	}

	public bool CanBuyGoods
	{
		get
		{
			return false;
		}
	}

	public bool CanBuyToken
	{
		get
		{
			return false;
		}
	}

	public bool CanBuyFree
	{
		get
		{
			return false;
		}
	}

	public bool LimitedTimeOffer
	{
		get
		{
			return false;
		}
	}

	public TreasureBoxItemInfo(BoxShop boxPd)
	{
	}

	public bool IsOnCoolTime()
	{
		return false;
	}

	public bool AvailableByGoods(int count, ref int costIdx, int prevCostIdx = -1)
	{
		return false;
	}

	public bool AvailableByFree()
	{
		return false;
	}

	public bool AvailableByOnlyToken(int count, ref int costIdx, int prevCostIdx = -1)
	{
		return false;
	}

	public bool AvailableByToken(int count, ref int costIdx, int prevCostIdx = -1)
	{
		return false;
	}

	public TimeSpan GetLastCooltime()
	{
		return default(TimeSpan);
	}

	public TimeSpan GetLastTermtime()
	{
		return default(TimeSpan);
	}

	public EnumData.GoodsType GetCostGoodsType(int costIdx)
	{
		return EnumData.GoodsType.None;
	}

	public bool IsStoryStampBox(int costIdx)
	{
		return false;
	}
}
