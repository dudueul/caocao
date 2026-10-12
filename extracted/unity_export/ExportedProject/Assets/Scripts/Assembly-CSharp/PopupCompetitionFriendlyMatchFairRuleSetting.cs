using UnityEngine;

public class PopupCompetitionFriendlyMatchFairRuleSetting : CaocaoUIModalPopup
{
	private static PopupCompetitionFriendlyMatchFairRuleSetting self;

	private FairRule m_fairRule;

	[SerializeField]
	private UIButton[] treasureEnhanceBtns;

	[SerializeField]
	private UIButton[] treasureEnhancePassiveBtns;

	[SerializeField]
	private UIButton[] runeBtns;

	[SerializeField]
	private UIButton[] runeSetPassiveBtns;

	[SerializeField]
	private UIButton[] runeEnhanceBtns;

	public static void Show(FairRule fairRule)
	{
	}

	private void UpdateUI()
	{
	}

	private void SetDim(UIButton button, bool pressed)
	{
	}

	private void SetDim(UIButton button, bool pressed, bool enable)
	{
	}

	private void UpdateButtons(UIButton[] buttons, int pressed)
	{
	}

	private void UpdateSubButtons(UIButton[] buttons, int pressed, params int[] enableIdxs)
	{
	}

	public void Apply()
	{
	}

	public void OnBtnTreasureEnhance0()
	{
	}

	public void OnBtnTreasureEnhance1()
	{
	}

	public void OnBtnTreasureEnhance2()
	{
	}

	public void OnBtnTreasureEnhancePassive0()
	{
	}

	public void OnBtnTreasureEnhancePassive1()
	{
	}

	public void OnBtnTreasureEnhancePassive2()
	{
	}

	public void OnBtnRune0()
	{
	}

	public void OnBtnRune1()
	{
	}

	public void OnBtnRuneSetPassive0()
	{
	}

	public void OnBtnRuneSetPassive1()
	{
	}

	public void OnBtnRuneEnhance0()
	{
	}

	public void OnBtnRuneEnhance1()
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
