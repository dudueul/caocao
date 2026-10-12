using UnityEngine;

public class RaidLevelSettingPopup : CaocaoUIModalPopup
{
	[SerializeField]
	private UILabelLocalized m_titleLabel;

	[SerializeField]
	private UITexture m_face;

	[SerializeField]
	private CaocaoTabButtons m_tab;

	[SerializeField]
	private UIButton m_summonBtn;

	[SerializeField]
	private UILabelLocalized m_rewardSliderValueLabel;

	[SerializeField]
	private UISlider m_rewardSlider;

	[SerializeField]
	private UILabelLocalized m_needRageLabel;

	[SerializeField]
	private UILabelLocalized m_rageLabel;

	[SerializeField]
	private UISlider m_rageSlider;

	private RaidLobby2 m_parent;

	private int m_raidType;

	private int m_UseType;

	private int m_curLevel;

	private int m_curRage;

	private int m_rewardMultiplier;

	private void Initialize()
	{
	}

	private void OnTabClicked(CaocaoButton button)
	{
	}

	public void OnOkBtnClicked()
	{
	}

	public void OnCancelBtnClicked()
	{
	}

	public static void Show(RaidLobby2 parent, int raidType)
	{
	}

	public void OnSliderLeftClicked()
	{
	}

	public void OnSliderRightClicked()
	{
	}

	public void OnRewardSliderUpdated()
	{
	}

	private void UpdateRewardSliderLabel()
	{
	}

	private void UpdateRewardSliderValue()
	{
	}

	private void UpdateNeedRageLabel()
	{
	}

	private void UpdateRageGauge()
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
