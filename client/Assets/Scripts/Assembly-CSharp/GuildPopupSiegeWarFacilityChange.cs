using LitJson;

public class GuildPopupSiegeWarFacilityChange : CaocaoUIModalPopup
{
	public UILabelLocalized m_labelDesc;

	public GuildSiegeWarFacilityListItem fortressFromUI;

	public GuildSiegeWarFacilityListItem facilityFromUI;

	public GuildSiegeWarFacilityListItem facilityToUI;

	protected GuildSiegeWarFacilityWar fortressFrom;

	protected GuildSiegeWarFacilityWar facilityFrom;

	protected GuildSiegeWarFacility facilityTo;

	public static void Show(GuildSiegeWarFacilityWar facilityFrom, GuildSiegeWarFacility facilityTo)
	{
	}

	protected virtual void UpdateUI()
	{
	}

	public void OnChangeBtn()
	{
	}

	public virtual void ChangeFacility()
	{
	}

	public void OnChangeResponse(JsonData response)
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
