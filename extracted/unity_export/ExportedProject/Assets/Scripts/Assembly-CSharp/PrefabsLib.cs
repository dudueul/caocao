using System.Collections.Generic;
using UnityEngine;

public class PrefabsLib
{
	private static Dictionary<string, GameObject> m_var2prefab;

	private const string tk2dMapObjectPrefabName_resource = "Prefabs/MapObjects/{0}";

	private const string AssetBundleUnitScreenFxNameFormat = "ScreenFx_{0}";

	private const string NonAssetBundleUnitScreenFxNameFormat = "Prefabs/Unitclass/ScreenFx_{0}";

	public const string m_corpsPortratePath = "Prefabs/GuildWarObjects/GuildWarCorpsPortrate";

	private const string guildwar_BuildPrefabPlace = "Prefabs/GuildWar_MapObject/{0}";

	public static GameObject annihilationWarLobby
	{
		get
		{
			return null;
		}
	}

	public static GameObject annihilationWarMatch
	{
		get
		{
			return null;
		}
	}

	public static GameObject battleFormationPreset
	{
		get
		{
			return null;
		}
	}

	public static GameObject annihilationWarRewardList
	{
		get
		{
			return null;
		}
	}

	public static GameObject annihilationWarRankingRewardListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject annihilationWarResultRewardListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject annihilationWarScoreDetail
	{
		get
		{
			return null;
		}
	}

	public static GameObject annihilationWarSeasonReward
	{
		get
		{
			return null;
		}
	}

	public static GameObject pvpScoreDetailListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject pvpArmyPositionInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupArmyPositionUpgrade
	{
		get
		{
			return null;
		}
	}

	public static GameObject fxArmyPositionUpgrade
	{
		get
		{
			return null;
		}
	}

	public static GameObject pvpUnitStatisticsPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject pvpUnitStatisticListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject pvpMapInfoListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject thousandBattleUnitGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject thousandBattlePassiveGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject thousandBattlePopupPassiveInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject fxThousandRecovery
	{
		get
		{
			return null;
		}
	}

	public static GameObject thousandBattleResult
	{
		get
		{
			return null;
		}
	}

	public static GameObject thousandBattlePopupReward
	{
		get
		{
			return null;
		}
	}

	public static GameObject thousandBattleRewardListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject thousandBattleRewardListItemDim
	{
		get
		{
			return null;
		}
	}

	public static GameObject passiveTooltipGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_prefabMoveSelectionObj
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_prefabAttackSelectionObj
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_prefabEffectSelectionObj
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_prefabItemSelectionObj
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_prefabTargetFocusungObj
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_prefabEnergyBarObj
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_prefabCustomLabelObj
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_skillIcon
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_itemIcon
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_treasureIcon
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_skillWithNameUI
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_skillUI
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemUI
	{
		get
		{
			return null;
		}
	}

	public static GameObject reuseItemUI
	{
		get
		{
			return null;
		}
	}

	public static GameObject goodUI
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitUI
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemListUI
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemOverUI
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitDamageFont
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitRecoverFont
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitMissFont
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitPoisonFont
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitManaDamageFont
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitManaRecoverFont
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitEpDamageFont
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitEpRecoverFont
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitGoldFont
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitBurnFont
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_corpsDamageHPFont
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_corpsHealHPFont
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_corpsMyDamageHPFont
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitExpectDmgLabel
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_infoMsgLabel
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_speedInfoLabel
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_battleFaceFx
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_attackCritFaceFx
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitSpActionLabel
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_ownTroopUnitPath
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_ownTroopUnitPathWithName
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_heroListUnit
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_heroListSortType
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_heroDeckListSortType
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitAppointmentProfile
	{
		get
		{
			return null;
		}
	}

	public static GameObject soldierDeckListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject arrangeDeckSetPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject arrangeDeckSetListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject SelectMercenaryPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupHeroListFilter
	{
		get
		{
			return null;
		}
	}

	public static GameObject filterAllButton
	{
		get
		{
			return null;
		}
	}

	public static GameObject filterNormalButton
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupRuneFilter
	{
		get
		{
			return null;
		}
	}

	public static GameObject runeFilterListitem
	{
		get
		{
			return null;
		}
	}

	public static GameObject runeFilterBtnAll
	{
		get
		{
			return null;
		}
	}

	public static GameObject runeFilterBtn
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupTreasureFilter
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureFilterListitem
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureFilterBtnAll
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureFilterBtn
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_tileTemplate
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_slotTemplate
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_tileMaker
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_tk2dUnit
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_tk2dUnitReflected
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitShadow
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_eventUnitShadow
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitMasking
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitAppearEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject spawnEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_turnUnDo1ActionUnit
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_bottomUnitInfoConditionIconSmall
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_bottomUnitInfoPassiveIconSmall
	{
		get
		{
			return null;
		}
	}

	public static GameObject bottomUnitInfoConditionIcon
	{
		get
		{
			return null;
		}
	}

	public static GameObject bottomUnitInfoPassiveIcon
	{
		get
		{
			return null;
		}
	}

	public static GameObject bottomUnitInfoTechIcon
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitConditionMarker
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_weather_serenity
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_weather_cloud
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_weather_cloud_world
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_weather_rain
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_weather_snow
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_weather_storm
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_weather_fog
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_weather_fog_world
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_touchEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_CharacterRoleNPC
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_CharacterRoleEnemy
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_CharacterRolePlayerLeader
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_CharacterRoleBoss
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_CharacterRoleElite
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_CharacterRoleSigleCombat
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_CharacterRoleBossTarget
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_CharacterRoleVIPTarget
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_CharacterRoleTargetName
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_CharacterRoleFocus
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_BasicRewardItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_SealRewardItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_StampingEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_UserLevUpEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_VipLevUpEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_VipLevUpGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_tileListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_tilePopupListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_myItemDetail
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_myItemListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_myItemListUnitNew
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_shopItemListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitNameListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_goodsTemplate
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_equippableUnitTemplate
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_itemTemplate
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_flowChartItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_flowChartEmptyItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_flowLine
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_Ocean
	{
		get
		{
			return null;
		}
	}

	public static GameObject ambitionGauge
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_duelUnitTemplate
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_duelStartScene
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_duelStartBattleLabel
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_playerTurn
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_enermyTurn
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_competitionTurn
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_brawlTurn
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_turnToolTip
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_toolTipItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_toolTipUnitSkill
	{
		get
		{
			return null;
		}
	}

	public static GameObject toolTipRecommendedUnits
	{
		get
		{
			return null;
		}
	}

	public static GameObject toolTipResearch
	{
		get
		{
			return null;
		}
	}

	public static GameObject toolTipResearchItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject battleStart
	{
		get
		{
			return null;
		}
	}

	public static GameObject battleTitle
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_scenario_face
	{
		get
		{
			return null;
		}
	}

	public static GameObject emoticon_talk_right
	{
		get
		{
			return null;
		}
	}

	public static GameObject emoticon_talk_left
	{
		get
		{
			return null;
		}
	}

	public static GameObject emoticon_think_right
	{
		get
		{
			return null;
		}
	}

	public static GameObject emoticon_think_left
	{
		get
		{
			return null;
		}
	}

	public static GameObject emoticon_frighten_right
	{
		get
		{
			return null;
		}
	}

	public static GameObject emoticon_frighten_left
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_UnitPopUpMessage
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_effect_phenix
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_worldMap_town
	{
		get
		{
			return null;
		}
	}

	public static GameObject worldMapInstance
	{
		get
		{
			return null;
		}
	}

	public static GameObject tacticMapTown
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_townUpgradeEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_townUpgradeShortEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_totalFoodOrSilverBuildingUpgradeEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_stateConquered
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_worldMap_openRegion1
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_worldMap_openRegion2
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_common_goods_popupBtn2
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_common_popupBtn4
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_food1
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_food2
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_food3
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_food_plus
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_silver1
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_silver2
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_silver3
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_silver_plus
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_all_food1
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_all_food2
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_all_food3
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_all_silver1
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_all_silver2
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_harvest_all_silver3
	{
		get
		{
			return null;
		}
	}

	public static GameObject harvestHonor
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_worldMap_citylist
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_townMap_citylist
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_worldMap_townListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_changeKing_unitListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_changeFace_unitListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_changeSkinUnitSpriteListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_moveCapitalEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitHeal_effect
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitTraining_listItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitTrainingInfoPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject popup_resetTrainedUnit
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitPromotion_listItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitTraining_effect1
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_unitTraining_effect2
	{
		get
		{
			return null;
		}
	}

	public static GameObject deadUnit
	{
		get
		{
			return null;
		}
	}

	public static GameObject ResurrectionUnit
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_getItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_getItemFx
	{
		get
		{
			return null;
		}
	}

	public static GameObject eventInformation
	{
		get
		{
			return null;
		}
	}

	public static GameObject underDescript
	{
		get
		{
			return null;
		}
	}

	public static GameObject placeInformationSimple
	{
		get
		{
			return null;
		}
	}

	public static GameObject placeInformation
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitIndroduction
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_gameSpec
	{
		get
		{
			return null;
		}
	}

	public static GameObject god1Cloud
	{
		get
		{
			return null;
		}
	}

	public static GameObject predictListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject predictListSmallTitle
	{
		get
		{
			return null;
		}
	}

	public static GameObject rewardGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject rewardListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject centerMiddlePopupText
	{
		get
		{
			return null;
		}
	}

	public static GameObject overlayCenterPopupText
	{
		get
		{
			return null;
		}
	}

	public static GameObject overlayCenterMiddleAutoPopupText
	{
		get
		{
			return null;
		}
	}

	public static GameObject bottomCenterPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject overlayCenterPopupTextNoInput
	{
		get
		{
			return null;
		}
	}

	public static GameObject mailPanel
	{
		get
		{
			return null;
		}
	}

	public static GameObject mailListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject historyPanel
	{
		get
		{
			return null;
		}
	}

	public static GameObject historyBattleListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject historyBattleText
	{
		get
		{
			return null;
		}
	}

	public static GameObject historyBattleAttackableListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject historyBattleShamInfoListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject historyEventListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject historyEventStorybookInfoListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject eventMissionShortcutListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject historyNoticeListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject historyBattleDetailListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject commonPopupHistory
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupEventListFilter
	{
		get
		{
			return null;
		}
	}

	public static GameObject ranking
	{
		get
		{
			return null;
		}
	}

	public static GameObject rankingReward
	{
		get
		{
			return null;
		}
	}

	public static GameObject rankTypeListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject rankTypeTitleListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject rankRewardPopupListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject rankGuildRankerListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject nationPanel
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitForm
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitFormChange
	{
		get
		{
			return null;
		}
	}

	public static GameObject stateRewardListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject chainEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject helpPanel
	{
		get
		{
			return null;
		}
	}

	public static GameObject helpPanelItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject missionInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject scenarioInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject achievementPanel
	{
		get
		{
			return null;
		}
	}

	public static GameObject achievementItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject minimissionGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupMissionComplete
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupDailyMission
	{
		get
		{
			return null;
		}
	}

	public static GameObject dailyMissionTopItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject dailyMissionDetailItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupMissions
	{
		get
		{
			return null;
		}
	}

	public static GameObject commonMissionListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject missionAttendanceListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject missionDetailItemSmall
	{
		get
		{
			return null;
		}
	}

	public static GameObject techItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popUpDialogWindowLeft
	{
		get
		{
			return null;
		}
	}

	public static GameObject popUpDialogWindowRight
	{
		get
		{
			return null;
		}
	}

	public static GameObject popUpSelectWindow
	{
		get
		{
			return null;
		}
	}

	public static GameObject cashShopGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGamePause
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelWorldTacticMap
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelCashShop
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelBoxShop
	{
		get
		{
			return null;
		}
	}

	public static GameObject gainVipPoint
	{
		get
		{
			return null;
		}
	}

	public static GameObject cashShopPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject cashShopPayment
	{
		get
		{
			return null;
		}
	}

	public static GameObject dwarfCanvas
	{
		get
		{
			return null;
		}
	}

	public static GameObject vipLvDesc
	{
		get
		{
			return null;
		}
	}

	public static GameObject staffRoll
	{
		get
		{
			return null;
		}
	}

	public static GameObject staffRollChild
	{
		get
		{
			return null;
		}
	}

	public static GameObject coupon
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitTreeInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitTreeInfoGroup
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitTreeInfoPassive
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitTreeInfoPassiveMayorEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitTreeInfoPassiveLordEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitTreeHeroSearchPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitTreeHeroSearchOverlapPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitSearchOverlapListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemInfoPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemEquipPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemStatListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemMultiSpActionListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemSpActionListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemJobTypeListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemDescListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject tailSkinDetailBtn
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemPlaceListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemHistoryListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemCraftPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemPlacePopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject townAttackableEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject tacticTownAttackableEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject townOccupyEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject townLostEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject townBossAppearEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject townBossRetreatEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitHistoryPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitListPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject attendPanel
	{
		get
		{
			return null;
		}
	}

	public static GameObject attendListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject attendPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject specialAttendPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject specialAttendListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject eventAttendPanel
	{
		get
		{
			return null;
		}
	}

	public static GameObject eventAttendPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject eventAttendListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject consecutiveAttendListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject shamBattlePopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject shamTownListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject shamRewardListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject shamRewardListItemInst
	{
		get
		{
			return null;
		}
	}

	public static GameObject skillListPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemListPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject passiveInfoPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject passiveEquipPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject passiveEquipGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureBookPanel
	{
		get
		{
			return null;
		}
	}

	public static GameObject settingPanel
	{
		get
		{
			return null;
		}
	}

	public static GameObject changeLeaderPanel
	{
		get
		{
			return null;
		}
	}

	public static GameObject changeFacePanel
	{
		get
		{
			return null;
		}
	}

	public static GameObject changeLeaderEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject btnDim
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelTacticStore
	{
		get
		{
			return null;
		}
	}

	public static GameObject exchangeShop
	{
		get
		{
			return null;
		}
	}

	public static GameObject storeItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemCraftEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitTrainingLevUpEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitPromotionEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelGuildMain
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelGuildMemberList
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelGuildMemberListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGetGulidReward
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelGuildBattle
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupChangeGuildNotice
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildList
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelGuildListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupApplyGuild
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelGuildInfomation
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupRecruitGuildMemberList
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelGuildApplicantList
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelGuildApplicantListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildMemberPosition
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildJoinConfig
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildCreate
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildMarkSelect
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildMarkListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildMemberInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelGuildHistory
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelHistoryListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelHistoryDecoItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupChoiceGuildIdentity
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildSharePresent
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildFairSharePresent
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildPresentCondition
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildMission
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildMissionListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildMissionReward
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupGuildContribution
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildMenuNavigator
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildItemTooltip
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildSiegeWarFacilityListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildSiegeWarDefenceUserListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildSiegeWarPopupHeadquarter
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildSiegeWarPopupAttackDeck
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildSiegeWarPopupFacilityReinforce
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildSiegeWarPopupFacilityManage
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildSiegeWarPopupFacilityListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildSiegeWarPopupFacilityChange
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildSiegeWarPopupFortressChange
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildPopupDonateAmount
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildPopupDonateType
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildInfoHistoryDayListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildInfoHistoryListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildInfoHistoryMultiListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject goodDescListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitTooltip
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitStatListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitDescListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitSubRewardListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemReinforceEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitReinforceEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject weaponChangeEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject armorChangeEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject kitChangeEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemEquipEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject rewardStamp
	{
		get
		{
			return null;
		}
	}

	public static GameObject areaInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject reinforceTooltip
	{
		get
		{
			return null;
		}
	}

	public static GameObject passiveTermsListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject stageSelectSubItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject stageSelectExplain
	{
		get
		{
			return null;
		}
	}

	public static GameObject stageSelectEnding
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemTooltip
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureEquipTooltip
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemRegisterTooltip
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureReinforcePassiveTooltip
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureEnahncePassiveList
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemTooltip2Reward
	{
		get
		{
			return null;
		}
	}

	public static GameObject passiveTooltip
	{
		get
		{
			return null;
		}
	}

	public static GameObject goodsTooltip2
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitTooltip2
	{
		get
		{
			return null;
		}
	}

	public static GameObject lordEffectTooltip
	{
		get
		{
			return null;
		}
	}

	public static GameObject mayorEffectTooltip
	{
		get
		{
			return null;
		}
	}

	public static GameObject storeItemListSortType
	{
		get
		{
			return null;
		}
	}

	public static GameObject undoEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject upgradeBuilding
	{
		get
		{
			return null;
		}
	}

	public static GameObject selectGovernor
	{
		get
		{
			return null;
		}
	}

	public static GameObject provokePopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject provokeListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject packmanPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject stateRewardPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject worldMapMenuNavigator
	{
		get
		{
			return null;
		}
	}

	public static GameObject storybookListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject storybookSelectPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject languagePopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitEnergyBar
	{
		get
		{
			return null;
		}
	}

	public static GameObject townShortcuts
	{
		get
		{
			return null;
		}
	}

	public static GameObject buttonEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject townEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject tutorialArrow
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitTreeListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject dialogBlackBg
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureBoxOpen
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureBoxOpen1
	{
		get
		{
			return null;
		}
	}

	public static GameObject moviePlayer
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureCraftPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureCraftResultPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureComposeResultPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureChangeResultPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureReinforceResultPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasurePassiveChangeResultPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureCreateResultPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureCreCompCompose
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureCreCompCreate
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureCreCompPiece
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureCreCompChange
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureCreCompReinforce
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureCreCompPassiveChange
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureCreCompTreasureCreate
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupTreasureCreatePassiveList
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureCreateListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureCreatePassiveListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject treasureCreCompTutorial
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupTreasureSelectPassive
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemTierGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject craftCountGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject obtainableTreasurePopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject itemCompose
	{
		get
		{
			return null;
		}
	}

	public static GameObject getUnitEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject getUnitOne
	{
		get
		{
			return null;
		}
	}

	public static GameObject loadingBlock
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupStageNeedUnit
	{
		get
		{
			return null;
		}
	}

	public static GameObject stageNeedUnitListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject tooltipUnitFaceGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitTreeItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject heroFilterBtn
	{
		get
		{
			return null;
		}
	}

	public static GameObject unitLevelPlusPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject getRewardPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject rankupGiftPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject rankupGiftGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupStampRewardList
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupStampRewardListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupAutoClearReward
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelVipLvReward
	{
		get
		{
			return null;
		}
	}

	public static GameObject vipLvRewardListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject vipLvRewardListItemDim
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupTreasureCreateSelect
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupTreasureCreate
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupQuickEquip
	{
		get
		{
			return null;
		}
	}

	public static GameObject quickEquipListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelInventory
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupRelationBook
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupRelationDetail
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupScenarioStart
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupBattleMapStart
	{
		get
		{
			return null;
		}
	}

	public static GameObject purchaseRelationEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject likeAbilityPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject likeAbilityStatePopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject likeAbilityStateListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupSeasonPass
	{
		get
		{
			return null;
		}
	}

	public static GameObject seasonPassListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupSeasonPassPay
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupSeasonPassReward
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupInstanceExpedition
	{
		get
		{
			return null;
		}
	}

	public static GameObject townPopularityPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject nationPopularityPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject popularityRecoverEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject riotOccurredPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject popularityReceivePopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject riotPlusPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject slider2BtnPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject newcombackUserMissionPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupTownInfoList
	{
		get
		{
			return null;
		}
	}

	public static GameObject townInfoListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject upgradeBuildingAll
	{
		get
		{
			return null;
		}
	}

	public static GameObject fxWorldMapTownUpgradeAll
	{
		get
		{
			return null;
		}
	}

	public static GameObject boardPanel
	{
		get
		{
			return null;
		}
	}

	public static GameObject boardRatingPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject lordGiftPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject lordGiftGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject eventLobbyState
	{
		get
		{
			return null;
		}
	}

	public static GameObject chatChannelListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject chatBlockListPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject chatBlockListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject chatGlobalChannelSetting
	{
		get
		{
			return null;
		}
	}

	public static GameObject chatStickerListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject chatListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject autoInstancePopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_tk2dUnitArranged
	{
		get
		{
			return null;
		}
	}

	public static GameObject bfDefenseDeckItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupBattleFieldQuickDefenceDeck
	{
		get
		{
			return null;
		}
	}

	public static GameObject growUpPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject customEventPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject customEventIngameListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject customEventUnitListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject worldEventRewardListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject worldEventRankingListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject worldEventRankingDetailListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject bingoEventCompleteRewardListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject bingoEventGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject bingoEventSpecialGridItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject bingoSpecialOpenEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject bingoSpecialCompleteEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidLobby
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidLobby2
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidBossLvSetting
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidPopupRanking
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidRankingListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidNormalListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidAwakenListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidAchievementPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidBossInfoPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidBossInfoListItemHead
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidBossInfoListItemBody
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidPassiveUnitItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject RaidAwakenReserveSetup
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupLocaleSetting
	{
		get
		{
			return null;
		}
	}

	public static GameObject localeItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject panelRuneShop
	{
		get
		{
			return null;
		}
	}

	public static GameObject runeTooltip
	{
		get
		{
			return null;
		}
	}

	public static GameObject runeListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject runeUI
	{
		get
		{
			return null;
		}
	}

	public static GameObject RunePassiveListPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject RunePassiveListListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject RuneDecompositionListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupRuneEquip
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupRuneEquipChange
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupRuneDecomposition
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupRuneUpgrade
	{
		get
		{
			return null;
		}
	}

	public static GameObject PopupRuneCreate
	{
		get
		{
			return null;
		}
	}

	public static GameObject RuneCompose
	{
		get
		{
			return null;
		}
	}

	public static GameObject PopupRuneCombine
	{
		get
		{
			return null;
		}
	}

	public static GameObject FinishScenario
	{
		get
		{
			return null;
		}
	}

	public static GameObject FinishBattleField
	{
		get
		{
			return null;
		}
	}

	public static GameObject FinishAnnihilationWar
	{
		get
		{
			return null;
		}
	}

	public static GameObject FinishRaid
	{
		get
		{
			return null;
		}
	}

	public static GameObject FinishSham
	{
		get
		{
			return null;
		}
	}

	public static GameObject FinishSiege
	{
		get
		{
			return null;
		}
	}

	public static GameObject FinishThousandBattle
	{
		get
		{
			return null;
		}
	}

	public static GameObject FinishInstance
	{
		get
		{
			return null;
		}
	}

	public static GameObject FinishCompetition
	{
		get
		{
			return null;
		}
	}

	public static GameObject FinishWorldBoss
	{
		get
		{
			return null;
		}
	}

	public static GameObject FinishGuildWar
	{
		get
		{
			return null;
		}
	}

	public static GameObject FinishRelationBattle
	{
		get
		{
			return null;
		}
	}

	public static GameObject FinishArena
	{
		get
		{
			return null;
		}
	}

	public static GameObject battleUnitInfoListPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject matchmakingPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionUnitListPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionHoldUnit
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionNormalUnit
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionBanPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionSelectOrderPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionOrderSelectUnit
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionBattleTimer
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionFriendlyAwait
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionFriendlyWatcherList
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionFriendlyJoin
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionFriendlyBattleMapSelect
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionFriendly
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionFriendlyFairRuleSetting
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionRewardInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionRewardInfoListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionBattleInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionBattleInfoRule
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionTierChange
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionStart
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionBattleEmoticon
	{
		get
		{
			return null;
		}
	}

	public static GameObject competitionReward
	{
		get
		{
			return null;
		}
	}

	public static GameObject stickerListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject stickerSetListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject userSpellPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject cellUserSpell
	{
		get
		{
			return null;
		}
	}

	public static GameObject cellUserSpellTurn
	{
		get
		{
			return null;
		}
	}

	public static GameObject noticeUserSpellPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupPlayUserSpell
	{
		get
		{
			return null;
		}
	}

	public static GameObject brawlChangeUnitListPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject brawlUnitConditionItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject brawlUnitStatisticsPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject brawlSupportUnitEffect
	{
		get
		{
			return null;
		}
	}

	public static GameObject watchReplayPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject watchReplayButtons
	{
		get
		{
			return null;
		}
	}

	public static GameObject watchReplayPreBattlePopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject watchWatingPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupSkinItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject inst_FaceList
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupTimeBox
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupTimeBoxInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject timeBoxInfoListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject timeBoxListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject timeBoxSlotListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject timeBoxOpen
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupLevelPlusMethod
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupVisit
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupTextInput
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupUserInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject userInfoPvpScoreItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject userInfoStorybookListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject storybookSeriesTypeListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupSubMenuButtons
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupShareBattle
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupPrivacySetting
	{
		get
		{
			return null;
		}
	}

	public static GameObject monthlyGoodsPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_corpsPortrate
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarCorpsBattleInformation
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarCorpsMenu1Line
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarCorpsMenu2Line
	{
		get
		{
			return null;
		}
	}

	public static GameObject PopupGuildWarBarracks
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarPassiveItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarPassiveListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarPopupChangeSkin
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarPopupPassiveList
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarPopupDeclareWar
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarPopupMapPreview
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarBattleHistory
	{
		get
		{
			return null;
		}
	}

	public static GameObject PopupGuildWarReady
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarPopupSiegeWeaponCreate
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarHelper
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarCorpsInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarCorpsSkillList
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarBuildingInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarCorpsInfoJobTypeListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarCorpsInfoNameListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject PopupGuildWarEnding
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarEndingDay
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarEndingLog
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarPopupRewardInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarCorpsPassiveListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject PopupGuildWarFocus
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarBattleNotice
	{
		get
		{
			return null;
		}
	}

	public static GameObject m_guildWarTreasureSuppliesPopup
	{
		get
		{
			return null;
		}
	}

	public static GameObject PopupGuildWarSeasonResult
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarBattleFieldTileMap
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarTileSightMask
	{
		get
		{
			return null;
		}
	}

	public static GameObject GuildWarFocusListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject worldBossState
	{
		get
		{
			return null;
		}
	}

	public static GameObject PopupWorldBoss
	{
		get
		{
			return null;
		}
	}

	public static GameObject worldBossShopListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject fxWorldBossGive
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupArenaSupply
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupArenaStageInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject arenaStageInfoPassiveList
	{
		get
		{
			return null;
		}
	}

	public static GameObject arenaSlot
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupArenaResult
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupArenaPassiveEquip
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupArenaSynergyInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupArenaShop
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupArenaShopPresetItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupArenaShopBonusItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupArenaUnitsInfo
	{
		get
		{
			return null;
		}
	}

	public static GameObject popupArenaUnitsInfoItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject arenaScenarioRecordItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildBoard
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildBoardWrite
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildBoardReplyListItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildBoardReplyCountTableItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject guildBoardReplyPageTableItem
	{
		get
		{
			return null;
		}
	}

	public static GameObject linkedGoodsPopup
	{
		get
		{
			return null;
		}
	}

	static PrefabsLib()
	{
	}

	public static void ClearPrefabs()
	{
	}

	public static GameObject GetPrefab(string path)
	{
		return null;
	}

	public static GameObject GetSkillPrefab(int skillId)
	{
		return null;
	}

	public static GameObject GetSkillSpcPrefab(int skillId)
	{
		return null;
	}

	public static GameObject GetSkillChainPrefab(int skillId)
	{
		return null;
	}

	public static GameObject GetSkillPrefab(string prefabName)
	{
		return null;
	}

	public static GameObject GetConditionInvokePrefab(EnumData.Conditions conditionID)
	{
		return null;
	}

	public static GameObject GetMapObjectPrefab(int mapObjectId)
	{
		return null;
	}

	public static GameObject GetEventMapEnvObjPrefab(string prefabName)
	{
		return null;
	}

	public static GameObject GetDuelDustPrefab()
	{
		return null;
	}

	public static GameObject GetDuelDashAttackEffect()
	{
		return null;
	}

	public static GameObject GetUnitScreenFxPrefab(EnumData.UnitScreenFx fxType)
	{
		return null;
	}

	public static GameObject GetHealingTileEffectPrefab(EnumData.ConditionAniType type)
	{
		return null;
	}

	public static GameObject GetTreasureCreCompChangeTier(int tier)
	{
		return null;
	}

	public static GameObject GetBuffDebuff(string fxName)
	{
		return null;
	}

	public static GameObject GetGuildWarFxPrefab(string fxName)
	{
		return null;
	}

	public static GameObject GetGuildWarBuildObj(string Name)
	{
		return null;
	}

	public static string GetGuildWarBuildObjectPrefabPath(string name)
	{
		return null;
	}
}
