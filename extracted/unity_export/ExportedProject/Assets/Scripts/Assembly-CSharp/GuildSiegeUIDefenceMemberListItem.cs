using System;
using UnityEngine;

public class GuildSiegeUIDefenceMemberListItem : MonoBehaviour
{
	public UILabelLocalized labelName;

	public UILabelLocalized labelAverageLv;

	public UILabelLocalized labelRecentDefenceScore;

	public UILabelLocalized labelRecentAnnScore;

	public UITexture[] arUnitFace;

	public GameObject objSelect;

	private Action<GuildSiegeUIDefenceMemberListItem> onClick;

	private int faceSlotNum;

	public ulong memberId { get; protected set; }

	public int defenceDeckSlot { get; protected set; }

	public void SetData(EnumData.GuildSiegeWarOrderType panelType, EnumData.GuildSiegeFacilitySlot facilitySlot, GuildMember member, int defenceDeckSlot, Action<GuildSiegeUIDefenceMemberListItem> onClick)
	{
	}

	public void OnRecon(GuildMember member)
	{
	}

	public void Select(bool select)
	{
	}

	private void OnClick()
	{
	}
}
