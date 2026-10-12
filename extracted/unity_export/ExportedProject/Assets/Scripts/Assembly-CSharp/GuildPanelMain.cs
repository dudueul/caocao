using System.Collections.Generic;
using UnityEngine;

public class GuildPanelMain : GuildUIPanel<GuildPanelMain>
{
	private const string PREFS_GUILD_LV_KEY = "GuildLevel_";

	private const string PREFS_GUILD_SUCCESSION_KEY = "GulidSuccession_";

	private const string PREFS_GUILDWAR_SEASON_KEY = "GuildWarSeasonKey_";

	public Transform trsJobMarkRoot;

	public UITexture texBg;

	public UITexture texOpeningMark;

	public UILabel labelOpeningGuildName;

	public UITexture texLvUpMark;

	public UILabelLocalized labelLvUpLevel;

	public UILabelLocalized labelLvUpMember;

	public UILabelLocalized labelLvUpShop;

	public UIScrollView scrollViewMOTD;

	public PlayMakerFSM sliderFsm;

	public GameObject objSetting;

	public GameObject objApplicantMark;

	public GameObject[] closeOffObjs;

	public GameObject btn8;

	public GameObject guildBoardBtn;

	private Transform guildSiegeWarBtnTrs;

	public GameObject guildSiegeWarAutoBtn;

	public int settingBtnSpace;

	private EnumData.GuildPermission[] arSettingTypes;

	private List<EnumData.GuildPermission> listPermission;

	private List<GameObject> listCreatedSettingButtons;

	private int origSettingBgHeight;

	private bool startAnimation;

	private int prevGuildLevel;

	protected override void OnGuildError(string err, ref bool handled)
	{
	}

	protected override void OnUpdateMe()
	{
	}

	protected override void OnUpdateMyGuild()
	{
	}

	protected override void UpdateApplicants()
	{
	}

	public override void ClosePanel()
	{
	}

	public void OnSettingClicked()
	{
	}

	public void OnInfoClicked()
	{
	}

	public void OnMissionClicked()
	{
	}

	public void OnShopClicked()
	{
	}

	public void OnStorageClicked()
	{
	}

	public void OnRankingClicked()
	{
	}

	public void OnBattleClicked()
	{
	}

	public void OnGuildBoardClicked()
	{
	}

	public static void ShowGuildSupportDeckUI()
	{
	}

	public static void ShowSiegeWarInitAttackDeckUI()
	{
	}

	public static void ShowSiegeWarAttackDeckUI()
	{
	}

	public static void ShowSiegeWarLobbyUI()
	{
	}

	public static void ShowSiegeWarMngUIForTest()
	{
	}

	public static void ShowSiegeWarMngUI()
	{
	}

	public static void ShowDonateUI()
	{
	}

	public void OnSuccessionClicked()
	{
	}

	private void OpenManagingPopup(EnumData.GuildPermission permission)
	{
	}

	private void ShowNeedGoldPopup()
	{
	}

	private void UpdateBg(int guildLevel)
	{
	}

	protected override void OnShow()
	{
	}

	private void InitiateSettingMenu()
	{
	}

	public void OnSettingBtnClicked()
	{
	}

	private void UpdateSuccessionButton()
	{
	}

	private void DoSucceedToMaster()
	{
	}

	private void ShowSucceedToMasterPopup()
	{
	}

	private void OnSucceedToMaster()
	{
	}

	public void GetSiegeWarScheduleAndApply()
	{
	}

	public void OnAutoApplyBtnClick()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void CheckGuildBattleButtonState()
	{
	}
}
