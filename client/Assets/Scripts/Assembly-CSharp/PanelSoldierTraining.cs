using System;
using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

internal class PanelSoldierTraining : CaocaoUIModalPopup
{
	public enum SoldlierTab
	{
		Soldiers = 0,
		Manager = 1
	}

	private const int guideUnitID = 1100172;

	private const int searchAreaCount = 3;

	private const int startSoldierLevel = 1;

	public UIScrollView m_scrollView;

	public UIGrid m_grid;

	private Transform m_selectedGrid;

	private int m_selectedSoldierId;

	private GameObject m_mercenary;

	private GameObject m_deckSetManage;

	public GameObject m_rightBoxObj;

	public PrefabInstance m_unitstatInst;

	public PrefabInstance m_troopstatInst;

	private SoldlierTab m_tabType;

	public CaocaoRadioButton m_soldierTabBtn;

	public CaocaoRadioButton m_managerTabBtn;

	protected override void Awake()
	{
	}

	public void ShowPanel(SoldierDeckListCell.UIType uiType, SoldlierTab tabType, bool needUpdate = false)
	{
	}

	private void Start()
	{
	}

	private void Init()
	{
	}

	private void InitTabBtn(CaocaoRadioButton tabBtn, SoldlierTab tabs)
	{
	}

	private void UpdateTabApply(SoldlierTab tabs)
	{
	}

	public static void UpdateTabActive(CaocaoRadioButton tabBtn, bool focus)
	{
	}

	private void UpdateCloseBtn(SoldierDeckListCell.UIType uiType)
	{
	}

	private void InitSoldierList()
	{
	}

	[DebuggerHidden]
	private IEnumerator SetSoldierList()
	{
		return null;
	}

	private void UpdateGrid(Transform gridTransform, Soldier soldier)
	{
	}

	private void SelectSoldier(int soldierId, Transform gridTransform)
	{
	}

	private void ShowSoldierInfo(int soldierId)
	{
	}

	private void SetEquipment(Transform tf, UserUnit soldierUnit, int jobType, int soldierLevel)
	{
	}

	private void OnItemClick(ItemUI itemUI)
	{
	}

	private void SetTraninigButton(Soldier soldier, int soldierLevel, Transform rock, Transform label)
	{
	}

	private void SetRecuruitButton(Transform label, Transform rock, Soldier soldier)
	{
	}

	private void SetPriceIcon(Transform tf, Soldier soldier, int soldierLevel, bool isTraining)
	{
	}

	private void SetSkillBtn(int jobType)
	{
	}

	public void DestroyPanel()
	{
	}

	private void ShowLevelUpEffect()
	{
	}

	[DebuggerHidden]
	private IEnumerator _ShowLevelUpEffect(GameObject popup)
	{
		return null;
	}

	private void ShowPurchaseFoodOrSilver(int needFood, int needSilver, Action callback)
	{
	}

	public void ShowUnitHistoryPanel()
	{
	}

	public void ShowUnitSkills()
	{
	}

	public void OnClickTraining()
	{
	}

	private void RequestTrainingSoldier(UserSoldier userSoldier, Soldier soldier)
	{
	}

	private void RequestRecruitSoldier(Soldier soldier, int soldierLevel)
	{
	}

	private bool IsLimitLevelForTraining(int soldierLevel)
	{
		return false;
	}

	private bool IsMaxSoldierLevel(int soldierLevel)
	{
		return false;
	}

	private int GetNextLevelForTraining(int soldierLevel)
	{
		return 0;
	}

	private UserUnit GetSoldierUserUnit()
	{
		return null;
	}

	private bool CheckGoodsEnough(Soldier soldier, int soldierLevel, bool isTraining)
	{
		return false;
	}

	private int GetCost(int value, int level)
	{
		return 0;
	}

	private void ApplySoldierDeckUIMode(SoldierDeckListCell.UIType uiType)
	{
	}

	private void OnUpdateSoldierDeckAssign()
	{
	}

	private void GetSoldierDeckManager()
	{
	}

	private void SendRequest()
	{
	}

	private void SendRecruitRequest()
	{
	}

	private void OnSoldierTraining(JsonData response)
	{
	}

	private void OnSoldierRecruit(JsonData response)
	{
	}

	private void UpdateSoldierDeckAssign()
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
