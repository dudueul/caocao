using System;

public class GuildPopupDonate : CaocaoUIModalPopup
{
	public enum Mode
	{
		Amount = 0,
		Type = 1
	}

	public UISlider sliderAmount;

	public UILabelLocalized labelAmount;

	public UILabelLocalized labelMaxAmount;

	public ClickHandler goldHandler;

	public ClickHandler silverHandler;

	private Action onYes;

	private Action onCancel;

	private EnumData.GoodsType goodsType;

	private int maxAmount;

	public bool isGoldSelect
	{
		get
		{
			return false;
		}
	}

	public int amount
	{
		get
		{
			return 0;
		}
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

	public static GuildPopupDonate Show(Mode mode, Action onYes, Action onCancel)
	{
		return null;
	}

	public static GuildPopupDonate Show(Mode mode, EnumData.GoodsType goodsType, int maxAmount, Action onYes, Action onCancel)
	{
		return null;
	}

	public void OnYes()
	{
	}

	public void OnCancel()
	{
	}

	public void OnMinus()
	{
	}

	public void OnPlus()
	{
	}

	public void OnSlider()
	{
	}
}
