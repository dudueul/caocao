using System;
using UnityEngine;

public class StorybookPurchaseButton : MonoBehaviour
{
	[SerializeField]
	private UISprite goodsSprite;

	[SerializeField]
	private UILabelLocalized priceLabel;

	public Action OnClickCallback;

	public void UpdateUI(EnumData.GoodsType goodsType, int price)
	{
	}

	private void OnClick()
	{
	}
}
