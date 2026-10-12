using UnityEngine;

public class CashShopPopup : CaocaoUIModalPopup
{
	private enum TabMenu
	{
		VipLevel = 0,
		PayHistory = 1
	}

	private TabMenu m_currentTab;

	private GameObject m_vipLevelPanel;

	private UIButton m_btnClose;

	private TabMenu currentTab
	{
		get
		{
			return TabMenu.VipLevel;
		}
		set
		{
		}
	}

	private GameObject vipLevelPanel
	{
		get
		{
			return null;
		}
	}

	private UIButton btnClose
	{
		get
		{
			return null;
		}
	}

	public static void Show()
	{
	}

	protected override void Awake()
	{
	}

	private void Start()
	{
	}

	public void OnTabChanged(CaocaoButton button)
	{
	}

	public void UpdateUI()
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

	private void FillVipLevelPanel()
	{
	}

	private void ClearVipLevelPanel()
	{
	}

	private void OnCloseBtnClicked()
	{
	}

	private void OnVipLvBtnClicked()
	{
	}

	private void OnHistoryBtnClicked()
	{
	}
}
