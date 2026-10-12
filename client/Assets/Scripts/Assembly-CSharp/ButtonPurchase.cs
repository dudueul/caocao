using UnityEngine;

public class ButtonPurchase : MonoBehaviour
{
	protected enum CostType
	{
		Good = 0,
		Item = 1
	}

	public UILabelLocalized Label;

	public UILabelLocalized Cost;

	public UISprite Icon;

	protected CostType m_costType;

	protected EnumData.GoodsType m_goodType;

	protected EnumData.ItemType m_itemType;

	protected string m_spriteName;

	protected int m_price;

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public void Initialize(EnumData.GoodsType goodType, int price)
	{
	}

	public void Initialize(EnumData.ItemType itemType, int price)
	{
	}

	public void UpdateUI()
	{
	}

	protected Color GetLabelColor(EnumData.GoodsType goodType, int totalPrice)
	{
		return default(Color);
	}

	protected Color GetLabelColor(EnumData.ItemType itemType, int totalPrice)
	{
		return default(Color);
	}
}
