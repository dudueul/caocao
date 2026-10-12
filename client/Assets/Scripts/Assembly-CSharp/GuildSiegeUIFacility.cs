using System;
using UnityEngine;

public class GuildSiegeUIFacility : MonoBehaviour
{
	public UILabelLocalized labelName;

	public UILabelLocalized labelState;

	public UISprite spriteImg;

	public GameObject objClear;

	public GameObject objSelect;

	public GameObject objMarker;

	public EnumData.GuildSiegeFacilitySlot facilitySlot;

	private Action<GuildSiegeUIFacility> onClick;

	public int facilityId { get; protected set; }

	public void Initiate(GuildData guildData, EnumData.GuildSiegeWarOrderType panelType, Action<GuildSiegeUIFacility> onClick)
	{
	}

	private void OnClick()
	{
	}

	public void Select(bool select)
	{
	}
}
