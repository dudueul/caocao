using System;
using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class PanelAreaInfo : PanelSelfRegister2UIComponentsMonobehaviour
{
	private UserTown m_userTown;

	private UserInstance m_userInstance;

	private BattleField m_battleField;

	private UserBattleField m_userBattleField;

	private bool m_init;

	private UILabelLocalized m_townName;

	private UILabelLocalized m_townType;

	private UILabelLocalized m_stateName;

	private StateConditionUI m_stateCondition;

	private UnitUI m_unitUI;

	private MayorEffectUI m_mayorEffectUI;

	private GameObject m_mayorUnit;

	private GameObject m_mayorLabel;

	private GameObject m_mayorEffect;

	private GameObject m_mayorEffectLabel;

	private GameObject m_topStateBG;

	private GameObject m_autoClear;

	private GameObject m_autoClearState;

	private GameObject m_topBattleFieldType;

	private UILabelLocalized m_battleFieldTypeLabel;

	private GameObject m_castleInfo;

	private GameObject m_otherCastleInfo;

	private GameObject m_townInfo;

	private GameObject m_instanceInfo;

	private GameObject m_battleFieldInfo;

	private UITable m_table;

	private UIScrollView m_instScrollView;

	private UILabelLocalized m_instName;

	private GameObject m_expireObj;

	private GameObject m_timeObj;

	private UILabelLocalized m_expire;

	private UILabelLocalized m_time;

	private UILabelLocalized m_instDesc;

	private GameObject m_itemObj;

	private GameObject m_itemGridObj;

	private UIScrollView m_itemScrollView;

	private UILabelLocalized m_castle;

	private UILabelLocalized m_castleEffect;

	private UILabelLocalized m_castleLabel;

	private UILabelLocalized m_size;

	private UILabelLocalized m_farmType;

	private UILabelLocalized m_farmLabel;

	private UILabelLocalized m_farmEffect;

	private UILabelLocalized m_marketType;

	private UILabelLocalized m_marketLabel;

	private UILabelLocalized m_marketEffect;

	private UILabelLocalized m_foodLabel;

	private UILabelLocalized m_foodEffect;

	private UILabelLocalized m_silverLabel;

	private UILabelLocalized m_silverEffect;

	private UILabelLocalized m_safeLabel;

	private UILabelLocalized m_safeEffect;

	private UILabelLocalized m_riot;

	private UILabelLocalized m_riotEffect;

	private GameObject m_castleDim;

	private UILabelLocalized m_otherCastleTitleLabel;

	private GameObject m_battleFieldRule;

	private UILabelLocalized m_battleFieldRuleLabel;

	private UILabelLocalized m_townDesc;

	private UILabelLocalized m_battleFieldStateLabel;

	private UILabelLocalized m_battleFieldTerritoryLabel;

	private UILabelLocalized m_battleFieldDefenseLabel;

	private RewardProgressbarUI m_battleFieldProduceReward1st;

	private RewardProgressbarUI m_battleFieldProduceReward2nd;

	private GameObject m_myBottom;

	private GameObject m_opponentBottom;

	private GameObject m_instanceBottom;

	private GameObject m_myBattleFieldBottom;

	private GameObject m_myBottomDim;

	private GameObject m_myBottomGoods;

	private UILabelLocalized m_myCastleFood;

	private UILabelLocalized m_myCastleSilver;

	private UISprite m_myCastleFoodGauge;

	private UISprite m_myCastleSilverGauge;

	private GameObject m_opponentBottomDim;

	private UILabelLocalized m_opponentBottomDimLabel;

	private UILabelLocalized m_opponentCastleFood;

	private UILabelLocalized m_opponentCastleSilver;

	private UISprite m_opponentCastleFoodGauge;

	private UISprite m_opponentCastleSilverGauge;

	private UIGrid m_opponentRewardGrid;

	private UIScrollView m_opponentRewardScollView;

	private RewardProgressbarUI m_myBFRewardUI1st;

	private UILabelLocalized m_myBFProductionLabel1st;

	private RewardProgressbarUI m_myBFRewardUI2nd;

	private UILabelLocalized m_myBFProductionLabel2nd;

	private UILabelLocalized[] m_instDay;

	private UILabelLocalized m_instRank;

	private GameObject m_costObj;

	private ItemUI m_costItem;

	private UILabelLocalized m_cost1;

	private UILabelLocalized m_cost2;

	private UISprite m_costIcon1;

	private UISprite m_costIcon2;

	private GameObject m_unitObj;

	private GameObject m_unitGridObj;

	private UIScrollView m_unitScrollView;

	private GameObject m_btnEnter;

	private GameObject m_btnRecon;

	private GameObject m_btnManageTown;

	private GameObject m_btnDefense;

	private GameObject m_btnBattleFieldDefense;

	private GameObject m_btnTax;

	private GameObject m_btnAutoClear;

	private GameObject m_btnBattleFieldRecon;

	public Action OnSetDefenseDeck;

	private bool m_started;

	private string m_maxOccupationTimeStr;

	public UserTown userTown
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public UserInstance userInstance
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public BattleField battleField
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public UserBattleField userBattleField
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static void Show(int townId)
	{
	}

	public static void ShowInstance(int instanceId)
	{
	}

	public static void ShowBattleField(int battleFieldId)
	{
	}

	public static void ClosePopup()
	{
	}

	public static void RefreshUI()
	{
	}

	private void Start()
	{
	}

	public override void OnInit()
	{
	}

	private void Update()
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void AddTimer()
	{
	}

	private void InitUI()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateMyTownTop()
	{
	}

	private void UpdateBattleFieldTop()
	{
	}

	private void UpdateOtherTownTop()
	{
	}

	private void UpdateInstanceTop()
	{
	}

	private void SetActiveTopTownInfos(bool active)
	{
	}

	private void UpdateInstanceInfo()
	{
	}

	private void UpdateCastleInfo()
	{
	}

	private void UpdateBattleFieldInfo()
	{
	}

	private void SetBattleFieldProductionInfo(RewardProgressbarUI rewardUI, EnumData.BattleFieldRewardType rewardType, int productionValue)
	{
	}

	private void UpdateTownInfo()
	{
	}

	private void SetBuildingInfos(UserTown userTown)
	{
	}

	private void SetBuildingInfo(UserTown userTown, UILabelLocalized label, UILabelLocalized desc, EnumData.TownBuildingType buildingType)
	{
	}

	private void UpdateMyAreaBottom()
	{
	}

	private void UpdateBattleFieldBottom()
	{
	}

	private void InitBabttleFieldOwnRewardUI(EnumData.BattleFieldRewardType rewardType, UILabelLocalized label, RewardProgressbarUI rewardUI)
	{
	}

	private void UpdateBattleFieldOwnRewardAmount()
	{
	}

	private void UpdateHarvestAmount()
	{
	}

	private void UpdateOpponentAreaBottom()
	{
	}

	private void UpdateInstanceBottom()
	{
	}

	private void SetInstanceCost(int cost, EnumData.GoodsType costType, ref int count)
	{
	}

	private void UpdateInstanceButtons()
	{
	}

	private void UpdateMyAreaButtons()
	{
	}

	private void UpdateBattleFieldButtons()
	{
	}

	private void UpdateOpponentAreaButtons()
	{
	}

	public void EnterInstance()
	{
	}

	public void AutoClearInstance()
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowPostEffect(int befUserRank, Instance instPD, JsonData response)
	{
		return null;
	}

	private void ShowUnknownAutoClearInstanceError()
	{
	}

	private void ShowNotAvailableInstanceError()
	{
	}

	private void ShowNotEnoughStampError()
	{
	}

	private void ShowNotEnoughTicketError()
	{
	}

	public void Recon()
	{
	}

	public void EnableReconBtn(bool isEnable)
	{
	}

	public void ManageTown()
	{
	}

	public void SetDefenceUnitDeck()
	{
	}

	public void ShowBattleInfo()
	{
	}

	public void SetBattleFieldDefenseDeck()
	{
	}

	public void CollectTax()
	{
	}

	private void SetDefenseBtn(int townId)
	{
	}

	private void ShowChangeMayorPopup(UnitUI unitUI)
	{
	}
}
