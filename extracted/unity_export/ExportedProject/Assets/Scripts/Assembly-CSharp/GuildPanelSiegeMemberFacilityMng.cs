using System;
using System.Collections.Generic;
using UnityEngine;

public class GuildPanelSiegeMemberFacilityMng : PanelSelfRegister2UIComponentsMonobehaviour
{
	[Serializable]
	public class ListObject : GuildUIMemberListObject
	{
		public GuildPanelSiegeMemberFacilityMng panel { get; set; }

		protected override GuildMemberCellData GenerateCellData(int index, GuildMember member)
		{
			return null;
		}

		protected override Func<GuildUIMemberCategory, GuildMemberCellData, GuildMemberCellData, int> GenerateCustomSortFunc()
		{
			return null;
		}
	}

	private static Color32 green;

	private static Color32 red;

	public ListObject memberListObject;

	public UIGrid gridFortress;

	public UIGrid gridFacility;

	public UIGrid gridCommonFacility;

	public UILabelLocalized labelUserFortress;

	public UILabelLocalized labelUserFacility;

	public UILabelLocalized labelNpcFortress;

	public UILabelLocalized labelNpcFacility;

	public UILabelLocalized labelFortressState;

	public UILabelLocalized labelFacilityState;

	private bool initiatedFacility;

	private Dictionary<EnumData.GuildSiegeFacilitySlot, GuildSiegeWarDefenceFacilityListItem> dicFacilities;

	private EnumData.GuildSiegeFacilitySlot[] order;

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	public static void Show()
	{
	}

	private void OnUpdateFacilities()
	{
	}

	private void OnUpdateMemberDecks()
	{
	}

	private void UpdateFacilityUI()
	{
	}

	public void UpdateMemberListUI()
	{
	}

	private void UpdateLabels()
	{
	}

	public void OnSelectFacility(EnumData.GuildSiegeFacilitySlot facilitySlot, GuildSiegeWarDefenceFacilityListItem item)
	{
	}

	public void OnSelectMember()
	{
	}

	private void UpdateFacilitySelection(GuildSiegeWarDefenceUserListItem.CellData memberData, bool select)
	{
	}

	public void OnConfirmDefenceButton()
	{
	}

	protected override void OnDestroy()
	{
	}
}
