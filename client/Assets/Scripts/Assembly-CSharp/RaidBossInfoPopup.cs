using UnityEngine;

public class RaidBossInfoPopup : CaocaoUIModalPopup
{
	[SerializeField]
	private CaocaoTabButtons m_tabBtn;

	[SerializeField]
	private UITable m_uiTable;

	[SerializeField]
	private UIScrollView m_uiScrollView;

	private EnumData.RaidType m_bossInfoType;

	public void Initialize(EnumData.RaidType raidType)
	{
	}

	private void UpdateTableUI()
	{
	}

	private void OnTabClicked(CaocaoButton button)
	{
	}

	public void OnArtificeInfoClicked()
	{
	}

	public void OnPassiveInfoClicked()
	{
	}

	public static void Show(EnumData.RaidType raidType)
	{
	}

	private string GetTabBtnName(EnumData.RaidType raidType)
	{
		return null;
	}

	private void ClearUITable()
	{
	}

	private void InitializeTabButtons()
	{
	}

	public static string GetRaidBossInfoIconName(EnumData.RaidType raidType)
	{
		return null;
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
