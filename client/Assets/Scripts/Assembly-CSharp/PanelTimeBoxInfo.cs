using UnityEngine;

public class PanelTimeBoxInfo : CaocaoUIModalPopup
{
	public enum Tab
	{
		Arena = 0,
		Competition = 1
	}

	public UIReuseGrid boxInfosGrid;

	[SerializeField]
	private UITexture tierIcon;

	[SerializeField]
	private UILabelLocalized tierName;

	[SerializeField]
	private GameObject prevButton;

	[SerializeField]
	private GameObject nextButton;

	[SerializeField]
	private UILabelLocalized desc;

	[SerializeField]
	private CaocaoTabButtons tabButton;

	private Tier currentTier;

	private Tab tab;

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

	public static void ShowUI(Tab tab)
	{
	}

	private void OnTabChanged(CaocaoButton btn)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateHeadUI()
	{
	}

	public void NextTier()
	{
	}

	public void PrevTier()
	{
	}

	private Tier GetNowTier()
	{
		return null;
	}

	private Tier GetNextTier()
	{
		return null;
	}

	private Tier GetPrevTier()
	{
		return null;
	}

	private Tier GetFirstTier()
	{
		return null;
	}
}
