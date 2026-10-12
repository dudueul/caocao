using System;
using LitJson;

public class UserOptions : Singleton<UserOptions>
{
	private bool m_quickSlot;

	private bool m_panelSimple;

	private bool m_showTargetHP;

	private bool m_chatOneLine;

	private bool m_chatEmoticon;

	private bool m_vibration;

	private bool m_showMiniMap;

	private bool m_selectUnitAuto;

	private bool m_showBuffIcon;

	private bool m_useOSFont;

	private bool m_vipAutoHarvest;

	private bool m_unitMoveWait;

	private bool m_hideOtherHeroTreasure;

	private bool m_dialogAuto;

	private bool m_optionBySpeed;

	private bool m_skipCriticalDialog;

	private bool m_applyCriticalSpeed;

	private bool m_skipPincerAtk;

	private bool m_skipSupportAtk;

	private EnumData.UnitMaterialType m_prevUnitMtrlType;

	private EnumData.UnitMaterialType m_unitMtrlType;

	private int m_vsync;

	private bool m_bgm;

	private bool m_soundEffect;

	private bool m_voice;

	private float m_bgmVolume;

	private float m_seVolume;

	private float m_voiceVolume;

	private bool m_voiceChatSpeaking;

	private bool m_voiceChatListening;

	private float m_voiceChatSpeakingVolume;

	private float m_voiceChatListeningVolume;

	private EnumData.VoiceChatCompetitionChannel m_voiceChatCompetitionChannel;

	private EnumData.VoiceChatGuildWarChannel m_voiceChatGuildWarChannel;

	private bool m_voiceChatMikeCompetition;

	private bool m_allNoti;

	private bool m_townFallenNoti;

	private bool m_taxMaxNoti;

	private bool m_freeTreasureBoxNoti;

	private bool m_instanceExpeditionNoti;

	private bool m_battlefieldPlunderedNoti;

	private bool m_raidawakenSummonNoti;

	private bool m_raidawakenSuccessNoti;

	private bool m_raidawakenFailedNoti;

	private bool m_worldBossContributionNoti;

	private bool m_worldBossInstanceNoti;

	private bool m_worldBossRewardBeginNoti;

	private bool m_worldBossRewardEndNoti;

	private bool m_autoLogin;

	private EnumData.AutoMoveStateType m_autoInstanceMoveStateType;

	private bool m_autoInstanceMoveStateOn;

	private bool m_debugSetting;

	private bool m_hudFPS;

	private bool m_debugLogin;

	private bool m_debugGuildWarEffectParticle;

	private bool m_debugGuildWarSound;

	private bool m_debugGuildWarEffect;

	private bool m_debugGuildWarFont;

	private bool m_specialPackageEnabled;

	private bool m_communityEnabled;

	private bool m_couponEnabled;

	private string m_customEventFilePath;

	private string m_customLoadingFilePath;

	private bool m_annihilationEnabled;

	private bool m_thousandBattleEnabled;

	private bool m_thousandBattlePassSectionEnabled;

	private bool m_guildEnabled;

	private bool m_treasureChangeEnabled;

	private bool m_treasurePieceEnabled;

	private bool m_treasureComposeEnabled;

	private int m_bulletin_Hide_Limit;

	private bool m_bulletinEnable;

	private int m_bulletin_Hide_Ratio;

	private bool m_treasureEnhanceEnabled;

	private bool m_chatEnabled;

	private bool m_chatRoomLocaleEnabled;

	private int m_CheckReturnUserConditionDays;

	private bool m_battleFieldEnabled;

	private int m_harvestAllInterval;

	private bool m_multiBattleEnabled;

	private int m_multiBattleTryLimit;

	private bool m_raidEnable;

	private bool m_raidBlueDragonEnable;

	private bool m_raidVermilionBirdEnable;

	private bool m_raidBlackTortoiseEnable;

	private bool m_raidWhiteTigerEnable;

	private bool m_raidAwakenEnable;

	private bool m_guildSiegeWarEnable;

	private bool m_runeEnable;

	private int m_RuneInvenMaxAmount;

	private bool m_runeComposeEnable;

	private bool m_unitSkinItemEnable;

	private int m_gmtZone;

	private DateTime m_continuousDailyGiftStartDate;

	private bool m_competitionEnabled;

	private bool m_userSpellEnabled;

	private bool m_competitionContinueEnabled;

	private bool m_competitionReplayEnabled;

	private bool m_competitionWatchEnabled;

	private int m_publicTier;

	private bool m_timeBoxImmediateOpenEnabled;

	private int m_subscriptionLimit;

	private bool m_resetUnitTrainingEnabled;

	private bool m_guildWarEnabled;

	private bool m_relationEnabled;

	private bool m_seasonpassEnabled;

	private bool m_arenaEnabled;

	private bool m_instanceExpeditionEnabled;

	private bool m_playAnimationOptionEnabled;

	private bool m_checkMaintenanceOnResume;

	private bool m_guildBoardEnabled;

	private EnumData.VoiceChatConfig m_voiceChatConfig;

	private bool m_subscriptionShopEnabled;

	private bool m_packageShopEnabled;

	private bool m_recommendShopEnabled;

	private EnumData.Locale m_voiceLanguage;

	private EnumData.UserInfoOpenRange m_replayOpenRange;

	public bool quickSlot
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool panelSimple
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool showTargetHP
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool chatOneLine
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool chatEmoticon
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool vibration
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool showMiniMap
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool selectUnitAuto
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool showBuffIcon
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool useOSFont
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool vipAutoHarvest
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool unitMoveWait
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool hideOtherHeroTreasure
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool dialogAuto
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool optionBySpeed
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool skipCriticalDialog
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool applyCriticalSpeed
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool skipPincerAtk
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool skipSupportAtk
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public EnumData.UnitMaterialType unitMtrlType
	{
		get
		{
			return EnumData.UnitMaterialType.Default;
		}
		set
		{
		}
	}

	public EnumData.UnitMaterialType prevUnitMtrlType
	{
		get
		{
			return EnumData.UnitMaterialType.Default;
		}
	}

	public int vsync
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool bgm
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool soundEffect
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool voice
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public float bgmVolume
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float seVolume
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float voiceVolume
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public bool voiceChatSpeaking
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool voiceChatListening
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public float voiceChatSpeakingVolume
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float voiceChatListeningVolume
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public EnumData.VoiceChatCompetitionChannel voiceChatCompetitionChannel
	{
		get
		{
			return EnumData.VoiceChatCompetitionChannel.MyWatcher;
		}
		set
		{
		}
	}

	public EnumData.VoiceChatGuildWarChannel voiceChatGuildWarChannel
	{
		get
		{
			return EnumData.VoiceChatGuildWarChannel.MyGuild;
		}
		set
		{
		}
	}

	public bool voiceChatMikeCompetition
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool allNoti
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool townFallenNoti
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool taxMaxNoti
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool freeTreasureBoxNoti
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool instanceExpeditionNoti
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool battlefieldPlunderedNoti
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool raidawakenSummonNoti
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool raidawakenSuccessNoti
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool raidawakenFailedNoti
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool worldBossContributionNoti
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool worldBossInstanceNoti
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool worldBossRewardBeginNoti
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool worldBossRewardEndNoti
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool autoLogin
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public EnumData.AutoMoveStateType autoInstanceMoveStateType
	{
		get
		{
			return EnumData.AutoMoveStateType.Left;
		}
		set
		{
		}
	}

	public bool autoInstanceMoveStateOn
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool debugSetting
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool hudFPS
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool debugLogin
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool debugGuildWarEffectParticle
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool debugGuildWarSound
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool debugGuildWarEffect
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool debugGuildWarFont
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool specialPackageEnabled
	{
		get
		{
			return false;
		}
	}

	public bool communityEnabled
	{
		get
		{
			return false;
		}
	}

	public bool couponEnabled
	{
		get
		{
			return false;
		}
	}

	public string customEventFilePath
	{
		get
		{
			return null;
		}
	}

	public string customLoadingFIlePath
	{
		get
		{
			return null;
		}
	}

	public bool annihilationEnabled
	{
		get
		{
			return false;
		}
	}

	public bool thousandBattleEnabled
	{
		get
		{
			return false;
		}
	}

	public bool thousandBattlePassSectionEnabled
	{
		get
		{
			return false;
		}
	}

	public bool guildEnabled
	{
		get
		{
			return false;
		}
	}

	public bool treasureChangeEnabled
	{
		get
		{
			return false;
		}
	}

	public bool treasurePieceEnabled
	{
		get
		{
			return false;
		}
	}

	public bool treasureComposeEnabled
	{
		get
		{
			return false;
		}
	}

	public int bulletinHideLimit
	{
		get
		{
			return 0;
		}
	}

	public bool bulletinEnable
	{
		get
		{
			return false;
		}
	}

	public int bulletinHideRatio
	{
		get
		{
			return 0;
		}
	}

	public bool treasureEnhanceEnabled
	{
		get
		{
			return false;
		}
	}

	public bool chatEnabled
	{
		get
		{
			return false;
		}
	}

	public bool chatRoomLocaleEnabled
	{
		get
		{
			return false;
		}
	}

	public int CheckReturnUserConditionDays
	{
		get
		{
			return 0;
		}
	}

	public bool battleFiledEnabled
	{
		get
		{
			return false;
		}
	}

	public int harvestAllInterval
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool multiBattleEnabled
	{
		get
		{
			return false;
		}
	}

	public int multiBattleTryLimit
	{
		get
		{
			return 0;
		}
	}

	public bool raidEnable
	{
		get
		{
			return false;
		}
	}

	public bool raidBlueDragonEnable
	{
		get
		{
			return false;
		}
	}

	public bool raidVermilionBirdEnable
	{
		get
		{
			return false;
		}
	}

	public bool raidBlackTortoiseEnable
	{
		get
		{
			return false;
		}
	}

	public bool raidWhiteTigerEnable
	{
		get
		{
			return false;
		}
	}

	public bool raidAwakenEnable
	{
		get
		{
			return false;
		}
	}

	public bool guildSiegeWarEnable
	{
		get
		{
			return false;
		}
	}

	public bool runeEnable
	{
		get
		{
			return false;
		}
	}

	public int runeInvenMaxAmount
	{
		get
		{
			return 0;
		}
	}

	public bool runeComposeEnable
	{
		get
		{
			return false;
		}
	}

	public bool unitSkinItemEnable
	{
		get
		{
			return false;
		}
	}

	public int GMTZONE
	{
		get
		{
			return 0;
		}
	}

	public bool continuousDailyGiftEnabled
	{
		get
		{
			return false;
		}
	}

	public bool competitionEnabled
	{
		get
		{
			return false;
		}
	}

	public bool userSpellEnabled
	{
		get
		{
			return false;
		}
	}

	public bool competitionContinueEnabled
	{
		get
		{
			return false;
		}
	}

	public bool competitionReplayEnabled
	{
		get
		{
			return false;
		}
	}

	public bool competitionWatchEnabled
	{
		get
		{
			return false;
		}
	}

	public int publicTier
	{
		get
		{
			return 0;
		}
	}

	public bool timeBoxImmediateOpenEnabled
	{
		get
		{
			return false;
		}
	}

	public int subscriptionLimit
	{
		get
		{
			return 0;
		}
	}

	public bool resetUnitTrainingEnabled
	{
		get
		{
			return false;
		}
	}

	public bool guildWarEnabled
	{
		get
		{
			return false;
		}
	}

	public bool relationEnabled
	{
		get
		{
			return false;
		}
	}

	public bool seasonpassEnabled
	{
		get
		{
			return false;
		}
	}

	public bool arenaEnabled
	{
		get
		{
			return false;
		}
	}

	public bool instanceExpeditionEnabled
	{
		get
		{
			return false;
		}
	}

	public bool playAnimationOptionEnabled
	{
		get
		{
			return false;
		}
	}

	public bool checkMaintenanceOnResume
	{
		get
		{
			return false;
		}
	}

	public bool guildBoardEnabled
	{
		get
		{
			return false;
		}
	}

	public EnumData.VoiceChatConfig voiceChatConfig
	{
		get
		{
			return (EnumData.VoiceChatConfig)0;
		}
	}

	public bool subscriptionShopEnabled
	{
		get
		{
			return false;
		}
	}

	public bool packageShopEnabled
	{
		get
		{
			return false;
		}
	}

	public bool recommendShopEnabled
	{
		get
		{
			return false;
		}
	}

	public EnumData.Locale language
	{
		get
		{
			return EnumData.Locale.NONE;
		}
		set
		{
		}
	}

	public EnumData.Locale voiceLanguage
	{
		get
		{
			return EnumData.Locale.NONE;
		}
		set
		{
		}
	}

	public EnumData.UserInfoOpenRange replayOpenRange
	{
		get
		{
			return EnumData.UserInfoOpenRange.All;
		}
		set
		{
		}
	}

	protected override void Awake()
	{
	}

	public void Load()
	{
	}

	public void ParseServerConfig(JsonData data)
	{
	}

	public void ParseUserOptions(JsonData data)
	{
	}

	public void UpdateNotiSetting()
	{
	}

	private void OnApplicationPause(bool pause)
	{
	}

	protected override void OnApplicationQuit()
	{
	}

	protected override void OnDestroy()
	{
	}
}
