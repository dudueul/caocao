using System.Collections;
using System.Diagnostics;
using Game.Setting;
using UnityEngine;

public class PanelSetting : CaocaoUIModalPopup
{
	private enum SettingTab
	{
		General = 0,
		Screen = 1,
		Noti = 2,
		Etc = 3,
		Debug = 4,
		Info = 5,
		Keyboard = 6
	}

	private enum VoiceTestStatus
	{
		Start = 0,
		WaitingForResponse = 1,
		End = 2
	}

	private SettingTab currentTab;

	public CaocaoTabButtons tabButtons;

	public GameObject m_generalTab;

	public GameObject m_screenTab;

	public GameObject m_notiTab;

	public GameObject m_etcTab;

	public GameObject m_debugTab;

	public GameObject m_infoTab;

	public GameObject m_keyboardTab;

	public ToggleButtonUI m_quickSlotToggle;

	public ToggleButtonUI m_panelSimpleToggle;

	public ToggleButtonUI m_showTargetHPToggle;

	public ToggleButtonUI m_chatOneLineToggle;

	public ToggleButtonUI m_vibrationToggle;

	public ToggleButtonUI m_showMiniMapToggle;

	public ToggleButtonUI m_unitAutoSelectToggle;

	public ToggleButtonUI m_showBuffIconToggle;

	public ToggleButtonUI m_useOSFontToggle;

	public ToggleButtonUI m_vipAutoHarvestToggle;

	public ToggleButtonUI m_unitMoveWaitToggle;

	public ToggleButtonUI m_hideOtherHeroTreasure;

	public UILabelLocalized m_dialogSpeedLabel;

	public ToggleButtonUI m_skipCriticalDialogToggle;

	public ToggleButtonUI m_applyCriticalSpeedToggle;

	public ToggleButtonUI m_skipPincerAtkToggle;

	public ToggleButtonUI m_skipSupportAtkToggle;

	public UILabelLocalized m_vsyncLabel;

	public UILabelLocalized m_vsyncDesc;

	public UISlider m_vsyncSlider;

	public GameObject m_bgmCheck;

	public GameObject m_soundEffectCheck;

	public GameObject m_voiceCheck;

	public GameObject m_voiceChatSpeakingCheck;

	public GameObject m_voiceChatListeningCheck;

	public UISlider m_bgmSlider;

	public UISlider m_soundEffectSlider;

	public UISlider m_voiceSlider;

	public UISlider m_voiceChatSpeakingIntensity;

	public UISlider m_voiceChatSpeakingSlider;

	public UISlider m_voiceChatListeningSlider;

	public UILabelLocalized m_unitMtrlLabel;

	public UILabelLocalized m_voiceChatSpeakingTestLabel;

	public UILabelLocalized m_voiceChatComeptitionChannelLabel;

	public UILabelLocalized m_voiceChatGuildWarChannelLabel;

	public UIResolutionSetting m_resolutionSetting;

	private bool notiOptionChanged;

	public ToggleButtonUI m_allNotiToggle;

	public ToggleButtonUI m_townFallenNotiToggle;

	public ToggleButtonUI m_taxMaxNotiToggle;

	public ToggleButtonUI m_freeTreasureBoxNotiToggle;

	public ToggleButtonUI m_battlefieldPlunderedNotiToggle;

	public ToggleButtonUI m_raidawakenSummonNotiToggle;

	public ToggleButtonUI m_raidawakenSuccessNotiToggle;

	public ToggleButtonUI m_raidawakenFailedNotiToggle;

	public ToggleButtonUI m_intanceExpeditionNotiToggle;

	public ToggleButtonUI m_worldBossContributionNotiToggle;

	public ToggleButtonUI m_worldBossInstanceNotiToggle;

	public ToggleButtonUI m_worldBossRewardBeginNotiToggle;

	public ToggleButtonUI m_worldBossRewardEndNotiToggle;

	public ToggleButtonUI m_autoLogin;

	public ToggleButtonUI m_gpgsNotiToggle;

	public GameObject notiRaid;

	public GameObject notiRaidSuccess;

	public GameObject notiRaidFail;

	public Transform m_couponBtn;

	public Transform m_creditBtn;

	public UIButton m_debugButton;

	public ToggleButtonUI m_debugToggle;

	public ToggleButtonUI m_fpsToggle;

	public ToggleButtonUI m_debugLogin;

	public ToggleButtonUI m_guildwarSound;

	public ToggleButtonUI m_guildwarEffect;

	public ToggleButtonUI m_guildwarFont;

	public ToggleButtonUI m_guildwarEffectParticle;

	public UILabelLocalized m_openRangeLabel;

	public GameObject m_openRangeLock;

	public Transform m_openRangePrev;

	public Transform m_openRangeNext;

	private bool isForceCompetitionReplayOpenTier;

	public UIKeyBindingSetting m_keyBindingSetting;

	public GameObject m_closeBtn;

	private AudioSource m_testAudioSource;

	private AudioSource m_testVoiceAudioSource;

	private VoiceTestStatus m_voiceTestStatus;

	private float m_amplitudeValue;

	private GameObject m_credit;

	public static void ShowPopup()
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

	protected override void Awake()
	{
	}

	private void Start()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	public void OnClickDebug()
	{
	}

	public void UpdateUI()
	{
	}

	private void InitGeneralTab()
	{
	}

	private void UpdateGeneralTab()
	{
	}

	public void SetQuickSlot(bool enable)
	{
	}

	public void SetPanelSimple(bool simple)
	{
	}

	public void SetShowTargetHPToggle(bool show)
	{
	}

	public void SetChatOneLine(bool show)
	{
	}

	public void SetVibration(bool enable)
	{
	}

	public void SetMinimap(bool show)
	{
	}

	public void SetUnitAutoSelect(bool auto)
	{
	}

	public void SetShowBuffIcon(bool show)
	{
	}

	public void SetUseOSFont(bool use)
	{
	}

	public void SetVipAutoHarvest(bool autoHarvest)
	{
	}

	public void SetUnitMoveWait(bool use)
	{
	}

	public void SetHideOtherHeroTreasure(bool use)
	{
	}

	public void ToggleDialogSpeed()
	{
	}

	public void SetOptionBySpeed(bool use)
	{
	}

	public void SetSkipCriticalDialog(bool use)
	{
	}

	public void SetApplyCriticalSpeed(bool use)
	{
	}

	public void SetSkipPincerAtk(bool use)
	{
	}

	public void SetSkipSupportAtk(bool use)
	{
	}

	private void EnableGeneralTab()
	{
	}

	public void SetLocale()
	{
	}

	private void InitScreenTab()
	{
	}

	private void UpdateScreenTab()
	{
	}

	private void UpdateVSync()
	{
	}

	private void UpdateSound()
	{
	}

	private void UpdateUnitMtrl(bool updateMtrl)
	{
	}

	public void SetVSync()
	{
	}

	public void PrevVSync()
	{
	}

	public void NextVSync()
	{
	}

	public void SetBGM()
	{
	}

	public void SetBgmVolume()
	{
	}

	public void SetSoundEffect()
	{
	}

	public void SetSoundEffectVolume()
	{
	}

	public void SetVoice()
	{
	}

	public void SetVoiceVolume()
	{
	}

	public void PrevUnitMtrl()
	{
	}

	public void NextUnitMtrl()
	{
	}

	public void OnMuteBtnVoiceChatSpeaking()
	{
	}

	public void OnMuteBtnVoiceChatLietening()
	{
	}

	public void OnTestBtnVoiceChatSpeaking()
	{
	}

	private void StartVoiceChatTestSpeaking()
	{
	}

	private void StopVoiceChatTestSpeaking()
	{
	}

	public void OnNextBtnVoiceChatCompetitionChannel()
	{
	}

	public void OnPrevBtnVoiceChatCompetitionChannel()
	{
	}

	public void OnNextBtnVoiceChatGuildWarChannel()
	{
	}

	public void OnPrevBtnVoiceChatGuildWarChannel()
	{
	}

	public void SetVoiceChatSpeakingVolume()
	{
	}

	public void SetVoiceChatListeningVolume()
	{
	}

	[DebuggerHidden]
	private IEnumerator ProcessAmplitudeValue()
	{
		return null;
	}

	private void InitNotiTab()
	{
	}

	private void UpdateNotiTab()
	{
	}

	public void SetAccountLink()
	{
	}

	public void OnLoginSucceed(string npsn, string npToken, string npaCode)
	{
	}

	public void SetAllNoti(bool enable)
	{
	}

	public void SetTownFallenNoti(bool enable)
	{
	}

	public void SetTaxMaxNoti(bool enable)
	{
	}

	public void SetFreeTreasureBoxNoti(bool enable)
	{
	}

	public void SetBattlefieldPlunderedNoti(bool enable)
	{
	}

	public void SetRaidawakenSummonedNoti(bool enable)
	{
	}

	public void SetRaidawakenSuccessNoti(bool enable)
	{
	}

	public void SetRaidawakenFailedNoti(bool enable)
	{
	}

	public void SetInstanceExpeditionNoti(bool enable)
	{
	}

	public void SetworldBossContributionNoti(bool enable)
	{
	}

	public void SetworldBossInstanceNoti(bool enable)
	{
	}

	public void SetworldBossRewardBeginNoti(bool enable)
	{
	}

	public void SetworldBossRewardEndNoti(bool enable)
	{
	}

	private void SetAutoLogin(bool show)
	{
	}

	private void SetGpgsLogin(bool login)
	{
	}

	public void LogoutAccount()
	{
	}

	public void WithdrawAccount()
	{
	}

	private void UpdateEtcTab()
	{
	}

	public void ShowHelp()
	{
	}

	public void ShowQNA()
	{
	}

	public void ShowNxArenaCustomerSupport()
	{
	}

	public void ShowAttendance()
	{
	}

	public void ShowCafe()
	{
	}

	public void ShowCoupon()
	{
	}

	public void ShowEtc()
	{
	}

	public void ShowCredit()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitAndTurnOnBGMonDestroy(string lastBgmName)
	{
		return null;
	}

	private void InitDebugTab()
	{
	}

	private void UpdateDebugTab()
	{
	}

	private void SetDebug(bool enable)
	{
	}

	private void SetFps(bool show)
	{
	}

	private void SetDebugLogin(bool show)
	{
	}

	private void SetDebugGuildWarSound(bool show)
	{
	}

	private void SetDebugGuildWarEffect(bool show)
	{
	}

	private void SetDebugGuildWarFont(bool show)
	{
	}

	private void SetDebugGuildWarEffectParticle(bool show)
	{
	}

	private void InitInfoTab()
	{
	}

	private void UpdateInfoTab()
	{
	}

	public void PrevOpenRange()
	{
	}

	public void NextOpenRange()
	{
	}

	private void InitKeyBindingTab()
	{
	}
}
