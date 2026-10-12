using UnityEngine;

public class PopupPrivacySetting : CaocaoUIModalPopup
{
	public UILabelLocalized labelUnit;

	public UILabelLocalized labelNation;

	public UILabelLocalized labelBattlemap;

	public UILabelLocalized labelCompetition;

	public UILabelLocalized labelArena;

	private EnumData.UserInfoOpenRange unitInfo;

	private EnumData.UserInfoOpenRange nationInfo;

	private EnumData.UserInfoOpenRange battlemapInfo;

	private EnumData.UserInfoOpenRange competitionInfo;

	private EnumData.UserInfoOpenRange arenaInfo;

	private EnumData.UserInfoOpenRange changedUnitInfo;

	private EnumData.UserInfoOpenRange changedNationInfo;

	private EnumData.UserInfoOpenRange changedBattlemapInfo;

	private EnumData.UserInfoOpenRange changedCompetitionInfo;

	private EnumData.UserInfoOpenRange changedArenaInfo;

	[SerializeField]
	private GameObject competitionInfoLock;

	[SerializeField]
	private Transform competitionInfoBtn;

	private bool isForceCompetitionReplayOpenTier;

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

	public static void Show()
	{
	}

	private void UpdateUI()
	{
	}

	public void OnBtnUnitInfo()
	{
	}

	public void OnBtnNationInfo()
	{
	}

	public void OnBtnBattlemapInfo()
	{
	}

	public void OnBtnCompetitionInfo()
	{
	}

	public void OnBtnArenaInfo()
	{
	}

	private void UpdatePrivacySetting()
	{
	}
}
