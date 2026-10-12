using LitJson;

public class GuildPopupSiegeWarFortressChange : GuildPopupSiegeWarFacilityChange
{
	public GuildSiegeWarFacilityListItem fortressToUI;

	protected GuildSiegeWarFacility fortressTo;

	public new static void Show(GuildSiegeWarFacilityWar fortressFrom, GuildSiegeWarFacility fortressTo)
	{
	}

	protected override void UpdateUI()
	{
	}

	public override void ChangeFacility()
	{
	}

	public new void OnChangeResponse(JsonData response)
	{
	}
}
