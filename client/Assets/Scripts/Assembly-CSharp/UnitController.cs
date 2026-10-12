using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using Caocao;
using Eppy;
using UnityEngine;
using nAI;

public class UnitController : MonoBehaviour
{
	private static readonly string UNIT_COLORBOX_NAME;

	private static GameObject ms_mainCamera;

	private static BattleCameraController ms_mainCameraCtl;

	private GameObject m_unitColorBox;

	private List<GameObject> m_additionalObjs;

	private List<ExpectDmg> m_expectDmgs;

	private Dictionary<UnitData, ExpectDmg> m_unit2ExpectDmg;

	private UnitData bestTarget;

	private QuickPathFinderContext _qpfContext;

	private static QuickPathFinderContext ms_qpfContext;

	private static QuickPathFinderContext ms_vipQpfContext;

	public bool isExUnitController;

	public bool isMapObjectController;

	private const float touchDistanceThreshold = 0.1f;

	private Vector3 mousedownPos;

	private float mousedownTime;

	private EnumData.Skills m_lastUseSkillId;

	private static readonly EnumData.Conditions[] PenaltyConditions;

	private static readonly EnumData.Conditions[] AllOncePenaltyConditions;

	public List<ExpectDmg> expectDmgs
	{
		get
		{
			return null;
		}
	}

	public UnitData unitData { get; private set; }

	public UnitAnimation unitAnim { get; private set; }

	public QuickPathFinderContext qpfContext
	{
		get
		{
			return null;
		}
	}

	public QuickPathFinderContext vipQpfContext
	{
		get
		{
			return null;
		}
	}

	public bool isShowingDefeatMessage { get; private set; }

	public EnumData.Skills lastUserSkillId
	{
		get
		{
			return EnumData.Skills.NONE;
		}
		set
		{
		}
	}

	static UnitController()
	{
	}

	protected virtual void Start()
	{
	}

	private void OnDestroy()
	{
	}

	private void OnMouseDown()
	{
	}

	[DebuggerHidden]
	public IEnumerator OnMouseUpAsButton()
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator SelectUnit()
	{
		return null;
	}

	public void OnMouseUpImpl()
	{
	}

	[DebuggerHidden]
	public IEnumerator Die(bool showAnimation, bool moveCamera)
	{
		return null;
	}

	public void OnDieAnimationFinish()
	{
	}

	public void Kill()
	{
	}

	public void Resurrect()
	{
	}

	private void CheckAchievementNCommonMission(UnitData unitData)
	{
	}

	public void InitPathFinder()
	{
	}

	[DebuggerHidden]
	public IEnumerator Move(IntVector2 toGridPos)
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator _Move(IntVector2 toGridPos)
	{
		return null;
	}

	public void GetMovableGrid(List<IntVector2> grids)
	{
	}

	[DebuggerHidden]
	public IEnumerator _Teleport(EnumData.Skills skillId, IntVector2 toGridPos)
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator _Fly(EnumData.Skills skillId, IntVector2 toGridPos)
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator MoveBattleMap(IntVector2 toGridPos, UnitDirection Direct, bool moveCamera = true)
	{
		return null;
	}

	public IntVector2 GetClosestMovableGrid(IntVector2 toGridPos)
	{
		return default(IntVector2);
	}

	[DebuggerHidden]
	public IEnumerator MoveScenarioMap(IntVector2 toGridPos)
	{
		return null;
	}

	public void ShowMoveSelection()
	{
	}

	private bool IsMovablePincerTargetAround(IntVector2 pincerPos, UnitData targetData, List<IntVector2> movableGrids)
	{
		return false;
	}

	private void ShowAttackRange()
	{
	}

	private GameObject CreateAuraRangeObj(Sprite sprite, Transform parent, Vector2 screenPos)
	{
		return null;
	}

	private void ShowBestTarget(Action.ActionType actionType, EnumData.Skills skillId, List<UnitData> targets, bool move)
	{
	}

	public void HideAllSelection()
	{
	}

	public void HideMoveSelection()
	{
	}

	public void HideAttackInfo()
	{
	}

	public void GetAttackableGrid(List<IntVector2> attackableGrids)
	{
	}

	public void GetAttackableGrid(List<IntVector2> attackableGrids, IntVector2 baseGridPos)
	{
	}

	public void GetExpendedAttackableGrid(List<IntVector2> grids, IntVector2 baseGridPos)
	{
	}

	public void ShowAttackSelection(bool withPincerSelection)
	{
	}

	public void ShowAttackSelection()
	{
	}

	public void ShowAttackSelectionWithPincer()
	{
	}

	public void HideAttackSelection()
	{
	}

	public void GetPincerTargetsGrid(List<IntVector2> pincerTargetsGrid)
	{
	}

	public void GetPincerTargetsGrid(List<IntVector2> pincerTargetsGrids, IntVector2 unitPos)
	{
	}

	public void ShowPincerSelection()
	{
	}

	private List<UnitData> GetExemptedDimmingUnitWhenPincer(IntVector2 targetUnitPos)
	{
		return null;
	}

	public void HidePincerSelection()
	{
	}

	public void GetArtificeAvailableGrids(int skillId, Vector2 unitPos, UnitData unitData, List<IntVector2> grids, List<bool> gridActivatoins)
	{
	}

	public void GetArtificeAvailableGrids(int skillId, IntVector2 unitPos, UnitData unitData, List<IntVector2> grids, List<bool> gridActivatoins)
	{
	}

	public bool IsAvailableArtificeTargetType(EnumData.TargetType targetType, UnitData targetUnitData, Caocao.UnitGroup ownGroup, Caocao.UnitGroup oppositGroup)
	{
		return false;
	}

	public bool IsAvailableArtificeTarget(int skillId, UnitData targetUnitData)
	{
		return false;
	}

	public void ShowEffectSelection(int skillId)
	{
	}

	private void CollectArtificeInfo(int skillId, List<IntVector2> grids, List<UnitData> exemptedDimmingUnits)
	{
	}

	private void GetExpectAtkInfo(UnitData defUnitData, UnitData baseTargetUnitData, int targetUnitCount, int targetIndex, bool isAddEffectGrids = false)
	{
	}

	private void ShowAtkInfo()
	{
	}

	private void GetExpectArtInfo(UnitData defUnitObj, float dmgPct, float artPct, EnumData.Skills skillId)
	{
	}

	private void GetExpectArtInfo(UnitData defUnitObj, float dmgPct, float artPct, EnumData.UnitResType dmgType, EnumData.Skills skillId)
	{
	}

	private void ShowArtInfo()
	{
	}

	private void ShowUseItemInfo(UnitData defUnitData, float dmgPct, int itemId)
	{
	}

	private void ShowUseItemInfo(UnitData defUnitData, float dmgPct, int itemId, EnumData.UnitResType dmgType)
	{
	}

	public void HideArtificeSelection()
	{
	}

	public void ShowMultiTargetNormalAttackSelection(GameObject targetUnitObj)
	{
	}

	public void ShowMultiTargetArtificeSelection(GameObject targetUnitObj)
	{
	}

	public void GetMultiTargetArtificeGrid(List<IntVector2> grids, GameObject targetUnitObj)
	{
	}

	public void GetUsingItemAvailableGrids(List<IntVector2> grids)
	{
	}

	public void ShowItemSelection()
	{
	}

	public void HideItemSelection()
	{
	}

	public void MoveAndAttack(IntVector2 targetUnitGridPos)
	{
	}

	[DebuggerHidden]
	private IEnumerator _MoveAndAttack(IntVector2 targetUnitGirdPos)
	{
		return null;
	}

	public void MoveAndPincer(IntVector2 targetUnitGridPos)
	{
	}

	[DebuggerHidden]
	private IEnumerator _MoveAndPincer(IntVector2 targetUnitGridPos)
	{
		return null;
	}

	private IntVector2 GetMoveAndAttackDestPosition(IntVector2 currentUnitGridPos, IntVector2 targetUnitGridPos, bool isPincer = false)
	{
		return default(IntVector2);
	}

	public bool CheckAvailableItemTarget(UnitData targetData, int itemId)
	{
		return false;
	}

	public void Attack(UnitData targetUnitObj, EnumData.AttackType attackType, bool isPincer)
	{
	}

	public virtual bool IsAvailableAttackByTargetObj(UnitData targetUnitData, bool isCounter = false)
	{
		return false;
	}

	private bool IsAvailableAttackByTargetObj(IntVector2 attackUnitGridPos, UnitData targetUnitData, bool isCounter = false)
	{
		return false;
	}

	public List<UnitData> GetAttackable()
	{
		return null;
	}

	public List<UnitData> GetAttackable(IntVector2 unitGridPos, List<UnitData> candidates, bool markTile = true)
	{
		return null;
	}

	public Tuple<UnitData, IntVector2> GetMoveAndAttackable()
	{
		return null;
	}

	public void ActionComplete(EnumData.UnitActionChangeReason reason)
	{
	}

	public void SkillAttack(UnitData targetUnitData, EnumData.AttackType attackType, EnumData.Skills skillID)
	{
	}

	public void OnSkillAttack(UnitData targetUnitData, EnumData.AttackType attackType, EnumData.Skills skillID)
	{
	}

	public bool IsAvailableQuickArtifice()
	{
		return false;
	}

	public bool CanUseQuickSkill(out string reason)
	{
		reason = null;
		return false;
	}

	public void UseItem(UnitData targetUnitObj, int itemID)
	{
	}

	[DebuggerHidden]
	private IEnumerator _UseItem(UnitData targetUnitObj, int itemID)
	{
		return null;
	}

	public bool IsAvailableAttack()
	{
		return false;
	}

	public bool IsAvailableAttack(IntVector2 gridPos)
	{
		return false;
	}

	public bool HasAnyConditions(params EnumData.Conditions[] conditions)
	{
		return false;
	}

	public bool HasAllConditions(params EnumData.Conditions[] conditions)
	{
		return false;
	}

	public bool IsAvailableAttackByTargetObj(GameObject unitObj, GameObject targetObj)
	{
		return false;
	}

	public bool IsAvailableArtifice()
	{
		return false;
	}

	public bool IsAvailableUserItem()
	{
		return false;
	}

	public bool IsAvailablePincer(IntVector2 gridPos)
	{
		return false;
	}

	public bool IsAvailablePincer()
	{
		return false;
	}

	public List<UnitData> GetPincerableUnits()
	{
		return null;
	}

	public List<UnitData> GetPincerableUnits(IntVector2 unitGridPos)
	{
		return null;
	}

	public bool IsPincerableAround()
	{
		return false;
	}

	public void LocateUnitPosition(IntVector2 gridPos)
	{
	}

	public void SetActiveConditionEffects(bool active)
	{
	}

	public void CreateUnitColorBox()
	{
	}

	public void UpdateUnitColorBox()
	{
	}

	public void ResetUnitStaus()
	{
	}

	public bool NeedConditionRelease()
	{
		return false;
	}

	[DebuggerHidden]
	public IEnumerator ConditionRelease()
	{
		return null;
	}

	private void Effects(List<Tuple<EffectBase, bool>> effects, UnitData targetUnit, EnumData.Conditions conditionId, bool wait)
	{
	}

	private void ApplyConditionWithSpAction(Dictionary<int, List<Tuple<EffectBase, bool>>> effects, UnitData target, EnumData.spAction spActionId, EnumData.Conditions conditionId)
	{
	}

	private void ApplyConditionsWithSpAction(Dictionary<int, List<Tuple<EffectBase, bool>>> effects, UnitData target, Caocao.Weather weatherId)
	{
	}

	private void ApplyAllConditionWithSpAction(Dictionary<int, List<Tuple<EffectBase, bool>>> effects, UnitData target, EnumData.spAction spActionId)
	{
	}

	private void ApplyConditionWithSpAction(Dictionary<int, List<Tuple<EffectBase, bool>>> effects, List<UnitData> targets, EnumData.spAction spActionId, EnumData.Conditions conditionId)
	{
	}

	private void ApplyAllConditionWithSpAction(Dictionary<int, List<Tuple<EffectBase, bool>>> effects, List<UnitData> targets, EnumData.spAction spActionId, bool up, bool waitEachUnit)
	{
	}

	public Dictionary<int, List<Tuple<EffectBase, bool>>> ConditionApply()
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator ConditionApply(Dictionary<int, List<Tuple<EffectBase, bool>>> effects)
	{
		return null;
	}

	private bool IsConditionPenalty(EnumData.Conditions conditionId)
	{
		return false;
	}

	public bool NeedConditionPenalty()
	{
		return false;
	}

	public bool NeedAllOnceConditionPenalty()
	{
		return false;
	}

	[DebuggerHidden]
	public IEnumerator ConditionPenalty()
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator AllOnceConditionPenalty()
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator FireIgnitionPenalty()
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator FireStayPenalty()
	{
		return null;
	}

	private List<SpActionBase> AddConditionBonusWithSpAction(List<SpActionBase> spActions, EnumData.spAction spActionId)
	{
		return null;
	}

	private List<SpActionBase> GetConditionBonus()
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator ConditionBonus()
	{
		return null;
	}

	public bool NeedTerrainBonus()
	{
		return false;
	}

	[DebuggerHidden]
	public IEnumerator TerrainBonus()
	{
		return null;
	}

	public bool NeedTerrainPenalty()
	{
		return false;
	}

	[DebuggerHidden]
	public IEnumerator ProcessTerrainPenalty()
	{
		return null;
	}

	public bool NeedEpBonus()
	{
		return false;
	}

	[DebuggerHidden]
	public IEnumerator EpBonus()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ApplyAndAnimatePenalty(EnumData.UnitResType resType, int amount)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ApplyAndAnimate(EnumData.ConditionAniType aniType, int conditionId, int deltaGauge)
	{
		return null;
	}

	private int GetPenaltyConditionHpDmgRate(EnumData.Conditions condition)
	{
		return 0;
	}

	private ShowDamage.DamageType GetPenaltyConditionDamageType(EnumData.Conditions condition)
	{
		return ShowDamage.DamageType.normal;
	}

	private int CheckPenaltyConditionDamageLimit(EnumData.Conditions condition, int rawDamage)
	{
		return 0;
	}

	[DebuggerHidden]
	private IEnumerator ApplyAndAnimateFireIgnitionDamage()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator DisplayPanelAnimation(EffectBase effect, UnitStateGaugeBar gaugeBar, ShowPanelAnimation.Target position, float initialDelay, float finalDelay)
	{
		return null;
	}

	public EffectBase ChangeUnitCondition(UnitData unit, int nAttribution, EnumData.Conditions eCondition)
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator AddUnitRangeCondition(int conditionId)
	{
		return null;
	}

	public UnitData FindImputeDamageTargetUnitObj()
	{
		return null;
	}

	public void SetUnitSortingOrder()
	{
	}

	public void SetEventUnitSortingOrder()
	{
	}

	private void CreateUnitRoleMarker()
	{
	}

	private void CreateUnitTourneyMarker()
	{
	}

	private void CreateUnitConditionMarker()
	{
	}

	public void SetUnitRoleMarkerActive(bool isActive)
	{
	}

	public void ShowUnitRoleMarkerActive(bool show)
	{
	}

	private void CreateCloneUnit()
	{
	}

	public void UseSkill(EnumData.Skills skillId)
	{
	}

	public void UseItemSkill(EnumData.Skills skillId)
	{
	}

	public void CheckBattleMapObject()
	{
	}

	private void ProcessBattleMapObjectEffect(bool exist, EnumData.Conditions conditionId)
	{
	}

	private void ShowSkillReadyRange()
	{
	}

	private GameObject CreateSkillReadyRangeObj(Sprite sprite, Transform parent, Vector2 screenPos)
	{
		return null;
	}
}
