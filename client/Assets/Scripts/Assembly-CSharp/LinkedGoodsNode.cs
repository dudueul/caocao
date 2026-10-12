using UnityEngine;

public class LinkedGoodsNode
{
	private CashShopGridItem gridItem;

	private Transform stateIcon;

	private Transform lockIcon;

	private Transform openIcon;

	private CashShopItemInfo cashShopItemInfo;

	private GoodsShopItemInfo goodsShopItemInfo;

	public int index { get; private set; }

	public LinkedGoods linkedGoodsPd { get; private set; }

	public UserLinkedGoodsNode userLinkedGoodsNode { get; private set; }

	public LinkedGoodsNode(int _index, LinkedGoods _linkedGoodsPd, CashShopGridItem _gridItem, Transform _stateIcon)
	{
	}

	public void UpdateUI(CashShopItemInfo _cashShopItemInfo, UserLinkedGoodsNode _userLinkedGoodsNode)
	{
	}

	public void UpdateUI(GoodsShopItemInfo _goodsShopItemInfo, UserLinkedGoodsNode _userLinkedGoodsNode)
	{
	}

	private void SetTooltipClippingComponents()
	{
	}
}
