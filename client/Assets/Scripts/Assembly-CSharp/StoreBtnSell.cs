using UnityEngine;

public class StoreBtnSell : MonoBehaviour
{
	private Transform m_money1;

	private UISprite m_money1Icon;

	private UILabelLocalized m_money1price;

	private EnumData.GoodsType m_one;

	private GoodsType2TotalPrice one;

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

	public void SetPriceType(EnumData.GoodsType firstType)
	{
	}

	public void UpdatePrice(StoreItem selected, int count)
	{
	}

	private void UpdatePrice(EnumData.GoodsType type, int price)
	{
	}

	private void UpdatePriceUI()
	{
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
