using LitJson;

public class PopupRiotPlusByNation : CaocaoUIModalPopup
{
	public UILabelLocalized m_centerText;

	public UILabelLocalized m_nationGrade;

	public UISlider m_slider;

	public UIButton m_minus;

	public UIButton m_plus;

	public UILabelLocalized m_thumbValue;

	public UILabelLocalized m_minValue;

	public UILabelLocalized m_maxValue;

	public UISprite m_currentGauge;

	public UISprite m_plusGauge;

	public UISprite m_availableGauge;

	public UIButton m_cancel;

	public UIButton m_yes;

	public UILabelLocalized m_price;

	public UISprite m_icon;

	private EnumData.PopularityRecoveryType recoverType;

	private int min;

	private int currentCount;

	private int currentStep;

	private int currentRecoveredCount;

	private int maxCount;

	private int maxStep;

	private int[] costs;

	private int totalCost;

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

	public static void Show(EnumData.PopularityRecoveryType type)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateCeterText()
	{
	}

	private void UpdateGradeText()
	{
	}

	private void InitButtons()
	{
	}

	private void InitSlider()
	{
	}

	public void OnThumbUpdated()
	{
	}

	public void OnPlus()
	{
	}

	public void OnPlusImpl(int count)
	{
	}

	public void OnMinus()
	{
	}

	public void OnMinusImpl(int count)
	{
	}

	private void UpdateButtonIcon()
	{
	}

	private void UpdateButtonPrice()
	{
	}

	public void OnClickPlusPopularity()
	{
	}

	private void OnManagePopularity(JsonData response)
	{
	}
}
