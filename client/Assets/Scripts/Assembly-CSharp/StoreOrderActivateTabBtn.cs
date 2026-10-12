using UnityEngine;

public class StoreOrderActivateTabBtn : MonoBehaviour
{
	private enum StoreOrder
	{
		Market = 5080001,
		Trade = 5080002,
		Vip = 5080003,
		Honor = 5080004,
		Material = 5080005,
		Sell = 5080006,
		Packman = 5080007,
		Num = 7,
		First = Market
	}

	private static bool[] storeOnFlag;

	private static bool initiated;

	private static void InitStoreOrderFlag()
	{
	}

	public static bool IsActivatedShopMode(EnumData.ShopMode shopMode)
	{
		return false;
	}

	public void SetActivate(bool activate)
	{
	}

	private void SetChildObjectActivate(GameObject childObj, bool activate)
	{
	}
}
