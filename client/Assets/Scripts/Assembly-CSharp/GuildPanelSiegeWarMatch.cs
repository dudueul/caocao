using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class GuildPanelSiegeWarMatch : PanelSelfRegister2UIComponentsMonobehaviour
{
	[Serializable]
	public class FlagUI
	{
		public ItemUI itemUI;

		public UILabelLocalized labelName;
	}

	private static GuildPanelSiegeWarMatch self;

	public UILabelLocalized labelFacilityName;

	public UILabelLocalized labelFacilityLv;

	public UILabelLocalized labelDefenceUserNum;

	public UILabelLocalized labelDefenceRateTotal;

	public UILabelLocalized labelDefenceRateRecent;

	public GuildSiegeUIFacility[] facilitySlots;

	public UIGrid gridDefenceUser;

	public FlagUI guildFlag;

	public FlagUI nationFlag;

	public GameObject btnDefenceMng;

	public GameObject btnReinforceBase;

	public GameObject btnDefenceSham;

	public GameObject btnFacilityMng;

	public GameObject btnDefenceSetting;

	public GameObject btnPoint;

	public GameObject btnAttack;

	public GameObject btnRecon;

	public GameObject btnChangePhase;

	public UILabelLocalized labelReconNum;

	private ulong guildId;

	private EnumData.GuildSiegeWarOrderType panelType;

	private GuildSiegeUIFacility selectedFacility;

	private GuildSiegeUIDefenceMemberListItem selectedMember;

	private GuildData guildData;

	private GuildSiegeWarWar war;

	public static bool testMode;

	public override void ShowPanel()
	{
	}

	[DebuggerHidden]
	private IEnumerator Init()
	{
		return null;
	}

	private void CheckInitFacilities()
	{
	}

	public override void ClosePanel()
	{
	}

	public static void Show(ulong guildId, EnumData.GuildSiegeWarOrderType type)
	{
	}

	public static void Show()
	{
	}

	public static void RefreshUI()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateMapUI()
	{
	}

	private void OnSelectDefenceUser(GuildSiegeUIDefenceMemberListItem memberUI)
	{
	}

	private void OnSelectFacility(GuildSiegeUIFacility facilityUI)
	{
	}

	private void UpdateDefenceInfoUI()
	{
	}

	private void UpdatePanelStateUI()
	{
	}

	public void GoSiegeWarButton()
	{
	}

	private void CheckGoBattle()
	{
	}

	private void ShowGuildSiegeWarAttackEntrySelect()
	{
	}

	public static void InitGuildSiegeBattleInfo()
	{
	}

	private void SetGuildSiegeBattleInfo()
	{
	}

	public void OnMemberFacilityMngButton()
	{
	}

	public void OnManageFacilityButton()
	{
	}

	public void OnChangeFacilityButton()
	{
	}

	public void SetDefenceDeckButton()
	{
	}

	public void ReconButton()
	{
	}

	public void SetPointButton()
	{
	}

	public void ChangePhaseButton()
	{
	}

	protected override void OnDestroy()
	{
	}
}
