using System;

public class CashShopProtectInfo
{
	public ProtectPurchasedInfo protectPurchasedInfo;

	private DateTime m_purchasedTime;

	public Protect m_pdData { get; private set; }

	public bool showMonthly { get; private set; }

	public bool showFirstPurchase { get; private set; }

	public bool onSale { get; private set; }

	public DateTime purchasedTime
	{
		get
		{
			return default(DateTime);
		}
		set
		{
		}
	}

	public bool canPurchaseNow
	{
		get
		{
			return false;
		}
	}

	public TimeSpan repurchaseLeftTime
	{
		get
		{
			return default(TimeSpan);
		}
	}

	public CashShopProtectInfo(int protectId, ref ProtectPurchasedInfo refInfo)
	{
	}

	public void SetPermanentData(int protectId)
	{
	}
}
