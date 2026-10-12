using UnityEngine;

public class GuildPopupSiegeWarFacilitySelect : CaocaoUIModalPopup
{
	public enum Mode
	{
		Management = 0,
		ChangeFrom = 1,
		ChangeTo = 2
	}

	public UILabelLocalized title;

	public UILabelLocalized desc;

	public UILabelLocalized target;

	public UIScrollView scrollView;

	public UIGrid grid;

	public GameObject btnConstruct;

	public GameObject btnReinforce;

	public GameObject btnSelect;

	private Mode m_mode;

	private GuildSiegeWarFacilityWar facilityWarToChange;

	private GuildSiegeWarFacilityListItem m_selectedItem;

	public GuildSiegeWarFacilityListItem selectedItem
	{
		get
		{
			return null;
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

	public static void Show(Mode mode)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateManagementUI()
	{
	}

	private void UpdateChangeFromUI()
	{
	}

	private void UpdateChangeToUI()
	{
	}

	public void OnSelectItem(GuildSiegeWarFacilityListItem item)
	{
	}

	public void OnReinforceFacility()
	{
	}

	public void OnConstructFacility()
	{
	}

	public void OnSelectFacility()
	{
	}
}
