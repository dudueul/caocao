using System;

public class GuildPopupFairPresent : CaocaoUIModalPopup
{
	public ItemUI itemUI;

	public UILabelLocalized labelItemName;

	public UISlider slider;

	public PressableButton subPressableBtn;

	public PressableButton addPressableBtn;

	public UILabelLocalized labelSliderValue;

	public UILabelLocalized labelMaxAmount;

	private bool isGood;

	private int itemId;

	private int maxAmount;

	private int stepAmount;

	private Action<CaocaoUIModalPopup, int> btnCallback;

	private const float SLIDER_SOUND_GAP = 0.01f;

	private int prevAmount;

	private float lastSliderSoundTime;

	private bool byButtonSlide;

	private int curAmount
	{
		get
		{
			return 0;
		}
		set
		{
		}
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public static void Show(bool isGood, int itemId, int maxAmount, int stepAmount, Action<CaocaoUIModalPopup, int> btnCallback)
	{
	}

	public void OnPresentClicked()
	{
	}

	public void OnSlideAmount()
	{
	}

	public void OnSubButton()
	{
	}

	public void OnSubButtonImpl(int count)
	{
	}

	public void OnAddButton()
	{
	}

	public void OnAddButtonImpl(int count)
	{
	}

	private void AddAmountSlider(int amount)
	{
	}

	protected override void OnDestroy()
	{
	}
}
