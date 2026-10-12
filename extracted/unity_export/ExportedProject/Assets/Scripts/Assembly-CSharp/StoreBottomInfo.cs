using System;
using UnityEngine;

public class StoreBottomInfo : MonoBehaviour
{
	public EnumData.ShopMode currentShopMode { get; private set; }

	private Transform tab01
	{
		get
		{
			return null;
		}
	}

	private Transform tab02
	{
		get
		{
			return null;
		}
	}

	private Transform tab03
	{
		get
		{
			return null;
		}
	}

	private Transform tab05
	{
		get
		{
			return null;
		}
	}

	private Transform tab04
	{
		get
		{
			return null;
		}
	}

	private Transform tabGuild
	{
		get
		{
			return null;
		}
	}

	private Transform currentTabInfoTransf
	{
		get
		{
			return null;
		}
	}

	private void ActivateBottomInfoObj()
	{
	}

	public void SetRefreshEventDelegateInfo(Action refreshFunc)
	{
	}

	private void ShowRefreshAnnoucePopup(Action refresh)
	{
	}

	private int GetRefreshCost(EnumData.ShopMode shopMode, int refreshCount)
	{
		return 0;
	}

	public void UpdateShopBottomInfo(EnumData.ShopMode shopMode)
	{
	}

	private void ShowMarketShopInfo()
	{
	}

	private void ShowTradeShopInfo()
	{
	}

	private void ShowVIPShopInfo()
	{
	}

	private void ShowSellShopInfo()
	{
	}

	private void ShowPackmanShopInfo()
	{
	}

	private void UpdateRefreshLeftTimeUIOnSec()
	{
	}

	private void UpdatePackmanShopInfo()
	{
	}

	private void ShowGuildShopInfo()
	{
	}

	private void ShowHonorShopInfo()
	{
	}

	private void RemoveTimers()
	{
	}

	private void OnDisable()
	{
	}
}
