using System.Runtime.InteropServices;
using UnityEngine;

public class StoreBtnPurchase : MonoBehaviour
{
	[StructLayout((LayoutKind)0, Size = 8)]
	private struct GoodsType2TotalPrice
	{
		public EnumData.GoodsType goodsType;

		public int totalPrice;

		public GoodsType2TotalPrice(EnumData.GoodsType type, int total)
		{
			goodsType = EnumData.GoodsType.None;
			totalPrice = 0;
		}
	}

	[StructLayout((LayoutKind)0, Size = 8)]
	private struct ItemTypeTotalPrice
	{
		public EnumData.ItemType itemType;

		public int totalPrice;

		public ItemTypeTotalPrice(EnumData.ItemType type, int total)
		{
			itemType = EnumData.ItemType.NONE;
			totalPrice = 0;
		}
	}

	private Transform m_money1;

	private UISprite m_money1Icon;

	private UILabelLocalized m_money1price;

	private Transform m_money2;

	private UISprite m_money2icon1;

	private UILabelLocalized m_money2price1;

	private UISprite m_money2icon2;

	private UILabelLocalized m_money2price2;

	private Transform m_money3;

	private UISprite m_money3Icon;

	private UILabelLocalized m_money3price;

	private GoodsType2TotalPrice one;

	private GoodsType2TotalPrice two;

	private ItemTypeTotalPrice costItem;

	private Transform money1
	{
		get
		{
			return null;
		}
	}

	private UISprite money1Icon
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized money1price
	{
		get
		{
			return null;
		}
	}

	private Transform money2
	{
		get
		{
			return null;
		}
	}

	private UISprite money2icon1
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized money2price1
	{
		get
		{
			return null;
		}
	}

	private UISprite money2icon2
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized money2price2
	{
		get
		{
			return null;
		}
	}

	private Transform money3
	{
		get
		{
			return null;
		}
	}

	private UISprite money3Icon
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized money3price
	{
		get
		{
			return null;
		}
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	private void OnDeserializeMyInfo()
	{
	}

	public void SetPriceType(EnumData.GoodsType firstType, EnumData.GoodsType secondType)
	{
	}

	public void SetPriceType(EnumData.ItemType costItemType)
	{
	}

	public void UpdatePrice(StoreItem selected, int count)
	{
	}

	private void UpdatePrice(EnumData.GoodsType type, int price)
	{
	}

	private void UpdatePrice(EnumData.ItemType type, int price)
	{
	}

	private void UpdatePriceUI()
	{
	}

	private Color GetLabelColor(EnumData.GoodsType goodsType, int totalPrice)
	{
		return default(Color);
	}

	private Color GetLabelColor(EnumData.ItemType itemType, int totalPrice)
	{
		return default(Color);
	}

	public void ResetTotalPrice()
	{
	}

	public void ResetTotalPrice(StoreItem selected)
	{
	}

	public void SetOnClickHandler(MonoBehaviour behaviour, string methodName)
	{
	}
}
