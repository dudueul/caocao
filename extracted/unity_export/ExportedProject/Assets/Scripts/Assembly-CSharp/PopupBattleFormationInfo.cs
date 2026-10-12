using System;

public class PopupBattleFormationInfo : CaocaoUIModalPopup
{
	public enum Mode
	{
		Purchase = 0,
		Upgrade = 1,
		Recon = 2
	}

	public UIBattleFormation battleFormationUi;

	public UILabelLocalized[] effectLabels;

	public UILabelLocalized[] effectValues;

	public UIButton purchaseButton;

	public UISprite purchaseGoodsTypeSprite;

	public UILabelLocalized purchaseCostLabel;

	public UIButton upgradeButton;

	public UIButton closeButton;

	public UIButton closeCenterButton;

	private Action onClose;

	public UserBattleFormation userBattleFormation { get; set; }

	public UserUnitGroup userUnitGroup { get; set; }

	public Mode mode { get; set; }

	public static void Show(UserBattleFormation userBattleFormation, UserUnitGroup userUnitGroup, Mode mode, Action onClose)
	{
	}

	private void UpdateUi()
	{
	}

	public void OnClickPurchase()
	{
	}

	public void OnClickUpgrade()
	{
	}

	private void OnCloseUpgrade()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}
}
