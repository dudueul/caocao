using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using Caocao;
using LitJson;
using UnityEngine;

public class ReconController : MonoBehaviour
{
	public enum ReconState
	{
		Atk = 0,
		Def = 1
	}

	public ReconState m_curReconState;

	private EnumData.TacticsType m_curDefenceTacticsType;

	private UserTowns myTowns;

	private RemainTimerEffect m_RemainTimerEffect;

	[HideInInspector]
	public GameObject m_reconSlotResource;

	[HideInInspector]
	public GameObject m_areaMaxTextResource;

	public GameObject m_slotCanvas;

	public GameObject m_BattleArrange_Attack;

	public GameObject m_lootMoneyObj;

	public UILabelLocalized m_lootMoneyTitle;

	public ReconLootInfoUI m_lootInfo1st;

	public ReconLootInfoUI m_lootInfo2nd;

	public UILabelLocalized m_changeMatchingMoney;

	public UILabelLocalized m_atkTownName;

	public UISprite m_changeMatchingMoneyIcon;

	public UIButton m_btnChangeMatching;

	public UIButton m_btnAttack;

	public UILabelLocalized m_remainTimeUI;

	public GameObject m_remainTimeObj;

	public GameObject m_battleFieldDefenseDeck;

	public GameObject m_battleFieldDefensePower;

	public UILabelLocalized m_bfDefensePowerLabel;

	public GameObject m_battleFieldDefenseScore;

	public UILabelLocalized m_bfDefenseScoreLabel;

	public GameObject m_BattleArrange_Defense;

	public GameObject m_objTownName;

	public GameObject m_objGuide;

	public GameObject m_objNumBg;

	public GameObject m_objDescText;

	public UILabelLocalized m_defTownName;

	public UILabelLocalized m_defGuideText;

	public Transform m_tacticTypeText;

	public UIButton m_radioTacticTypeA;

	public UIButton m_radioTacticTypeB;

	public UIButton m_btnCityManage;

	public UIButton m_btnDefense;

	public UIButton m_btnUnitform;

	public UIButton m_btnBattleFieldConfirm;

	public UIButton m_btnBattleFieldDefense;

	public UIButton m_btnBattleFieldUnitform;

	public UIButton m_btnObstacleForm;

	public GameObject m_objBattleFieldUnitNum;

	public UILabelLocalized m_labelBattleFieldUnitNum;

	public PanelBattleFieldDefense m_BattleField_Defense;

	public UILabelLocalized m_battlefieldDefenseDeckCombatPower;

	public GuildUISiegeWarDefSettingBoard m_GuildSiege_Board;

	public GuildPopupSiegeWarFacilityDefSetting m_GuildSiege_FacilityDefence;

	public GuildPopupSiegeWarDefenceFacilitySelect m_GuildSiege_SelectFacility;

	private List<GameObject> m_ownSlots;

	private List<GameObject> m_enemySlots;

	private static string ms_ownSlotNameFormat;

	private List<EventCmdBase> m_defenseDeckCmds;

	private List<UnitData> m_defenseDeckUnits;

	private static string ms_defenderOwnSlotNameFormat;

	private static string ms_defenderEnemySlotNameFormat;

	private static string ms_enemySlotNameFormat;

	private List<UnitData> m_defenderAllyUnits;

	private List<GameObject> m_ownBattleFieldSlots;

	private List<GameObject> m_ownBattleFieldGroupCounts;

	private List<EventCmdBase> m_defenseBattleFieldDeckCmds;

	private List<UnitData> m_defenseDeckSoldiers;

	[SerializeField]
	private Transform m_topRightAnchor;

	[SerializeField]
	private Transform m_battleDeckTopRightAnchor;

	private Transform m_topMenuTransform;

	public ReconState curReconState
	{
		get
		{
			return ReconState.Atk;
		}
		set
		{
		}
	}

	public EnumData.TacticsType curDefenceTacticsType
	{
		get
		{
			return EnumData.TacticsType.A;
		}
		set
		{
		}
	}

	private GameObject m_matchingInfo
	{
		get
		{
			return null;
		}
	}

	private Transform topMenuTransform
	{
		get
		{
			return null;
		}
	}

	private void UpdateButtonState(UIButton button, bool selected)
	{
	}

	private void Start()
	{
	}

	public void FocusLeaderUnit()
	{
	}

	public void SetTacticRouteUI()
	{
	}

	private int GetTacticCounts()
	{
		return 0;
	}

	private int GetChangeMatchingCost()
	{
		return 0;
	}

	private EnumData.GoodsType GetChangeMatchingGoodsType()
	{
		return EnumData.GoodsType.None;
	}

	public void SetTimer()
	{
	}

	private void UpdateRemainTimer()
	{
	}

	private void OnApplicationPause(bool pauseState)
	{
	}

	private void ShowTimeUpPopup()
	{
	}

	private void UpdateAtkUI()
	{
	}

	private void UpdateDefUI()
	{
	}

	public void UpdateAndDrawUnitSlots()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitUnitSetting()
	{
		return null;
	}

	private void RunEventCmd0x45()
	{
	}

	public void CreateOwnSlots()
	{
	}

	public void CreateDefenderOwnSlots()
	{
	}

	public void CreateDefenderEnemySlots()
	{
	}

	public void CreateEnemySlots()
	{
	}

	public void CreateEnemyUnits()
	{
	}

	private void CreateEnemyNPCUnits()
	{
	}

	private void CreateBattleFieldEnemyNPCUnits()
	{
	}

	private void CreateTowerUnits()
	{
	}

	public void CreateAllyUnits()
	{
	}

	public void CreateOwnUnitsOnSlot()
	{
	}

	public void CreateMapOjects()
	{
	}

	public void PlayBGM()
	{
	}

	public void StopBGM()
	{
	}

	public void CreateDefenderOwnUnitsOnSlot()
	{
	}

	public void CreateDefenderAllyUnits()
	{
	}

	private void RevertEventCmdByType(Type type)
	{
	}

	private void RunEventCmdByType(Type type)
	{
	}

	private bool SwitchingSpecificCmd(EventCmdBase cmd, bool active)
	{
		return false;
	}

	private void UpdateMatchingMoneyUI()
	{
	}

	private void UpdateLootUI()
	{
	}

	private void InitBattleFieldLootUI(ReconLootInfoUI lootInfo, UserBattleField userBattleField, int productionValue, EnumData.BattleFieldRewardType rewardType)
	{
	}

	private void UpdateMatchingInfo()
	{
	}

	private void UpdateTownNameUI()
	{
	}

	private void UpdateButtonUI()
	{
	}

	private PanelPopupBase.PopupState RenewRecon()
	{
		return PanelPopupBase.PopupState.KeepOpen;
	}

	private void GoWorldMapImpl()
	{
	}

	public void GoWorldmap()
	{
	}

	private PanelPopupBase.PopupState GoWorldmapHandler()
	{
		return PanelPopupBase.PopupState.KeepOpen;
	}

	public void GoTown()
	{
	}

	public void ShowSoldierDeck()
	{
	}

	public void OpenObstacleList()
	{
	}

	public void OpenSpecialObstaclePopup(int unitId, EnumData.GuildSiegeObjectType objType, Action<int, int> callback)
	{
	}

	public void RematchingEnemy()
	{
	}

	private void OnUseSilverToChangeMatchingUser(JsonData response)
	{
	}

	public void CloseBattleDeck()
	{
	}

	public void OpenBattleDeck()
	{
	}

	private void ResponseGetBattleFieldUnits(JsonData response)
	{
	}

	private void UpdateBattlefieldDefenseDeckCombatPower()
	{
	}

	public void OnClickTypeA()
	{
	}

	public void OnClickTypeB()
	{
	}

	private void OnTypeButtonClicked(EnumData.TacticsType newTacticType)
	{
	}

	private void UpdateTacticType(EnumData.TacticsType tacticType)
	{
	}

	private void OnUpdateTacticComplete(JsonData response)
	{
	}

	private void CreateBattleFieldDefenderOwnSlots()
	{
	}

	public void CreateBattleFieldDefenderOwnUnitsOnSlot()
	{
	}

	public void CreateBattleFieldDefenderAllySoldier()
	{
	}

	private void CreateBattleFieldDefenseUnitInAdvance()
	{
	}

	public void ShowBattleFieldOwnSlot(bool isShow, int bfGroup, int slotNum)
	{
	}

	public void ShowBattleFieldOwnSlotAll(bool isShow, int bfGroup)
	{
	}

	public IntVector2 GetBattleFieldGridPos(int groupIndex, int pos)
	{
		return default(IntVector2);
	}

	public void OnBtnBattleFieldConfirm()
	{
	}

	private void UpdateBattleFieldUnitCount()
	{
	}

	public void OpenUnitList()
	{
	}

	public void ActiveReconUIs(bool isActive, bool isUnitArrangeMode = false)
	{
	}

	public UnitDirection GetDefenseDeckDirection(int groupId)
	{
		return UnitDirection.back;
	}

	public void ActiveBattleFieldGroupCountUI(bool isActive)
	{
	}

	private void CreateGuildSiegeDefenderOwnSlots()
	{
	}

	public void CreateGuildSiegeDefenderOwnUnitsOnSlot()
	{
	}

	private void ChangeTopMenuAnchor(Transform setAnchor)
	{
	}
}
