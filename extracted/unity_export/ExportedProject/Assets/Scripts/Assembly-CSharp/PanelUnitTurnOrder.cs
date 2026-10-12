using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class PanelUnitTurnOrder : CaocaoUIPanel
{
	private enum Type
	{
		Normal = 0,
		Compact = 1,
		Watch = 2,
		Count = 3
	}

	private Type m_type;

	private GameObject[] m_toggleObj;

	private UnitTurnSlotUI[][] m_slots;

	private UnitTurnOrderUI[][] m_orders;

	private UnitTurnOrderUI[] m_tempOrders;

	private Collider[] m_toggleButton;

	private EnumData.StatType m_descType;

	private int m_selectedSlotIdx;

	private bool changedUnitStat;

	private List<UnitData> prevUnits;

	private bool orderAnimating;

	private bool needToToggle;

	private Vector3[][] orderPositions;

	private bool ableSingleOrder;

	private bool m_started;

	private UIToggle m_currentTurnCheck;

	private UILabelLocalized m_unitName;

	private UILabelLocalized[] m_unitStatValue;

	private UnitStatMeter m_unitStatMeter;

	private ItemUI[] m_unitTreasureItemUI;

	private PassiveUI[] m_unitPassiveUI;

	private RunePassiveUI[] m_unitRunePassiveUI;

	private RunePassiveUI m_unitRuneSetPassiveUI;

	private UnitData selectByWatcher;

	private bool initiated;

	private int TypeMax
	{
		get
		{
			return 0;
		}
	}

	private int TypeIndex
	{
		get
		{
			return 0;
		}
	}

	private void Start()
	{
	}

	private void Toggle()
	{
	}

	public override void OnInit()
	{
	}

	private void UpdateDesc()
	{
	}

	protected override void OnDestroy()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	private void InitWatchReplayPanel()
	{
	}

	private void InitUI()
	{
	}

	private int GetSingleOrderIndex(int idx)
	{
		return 0;
	}

	private int GetDefOrderIndex(int idx)
	{
		return 0;
	}

	private void SelectOrderUI(int idx)
	{
	}

	private void OnChangedUnitStat(UserUnit userUnit)
	{
	}

	private void OnBattleEnd()
	{
	}

	private void InstantiateUnitCard(UnitData unitData, int toggleIdx, int idx, bool single)
	{
	}

	private void UpdateUI()
	{
	}

	private void StoreUnits(List<UnitData> units)
	{
	}

	private void UpdateUnitOwner()
	{
	}

	private void OnUnitActionCompleted()
	{
	}

	private void OnUnitTagCompleted()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	private void FocusUnit(GameObject unitObj)
	{
	}

	private void SelectUnit(UnitData seletedUnit)
	{
	}

	private void LateUpdate()
	{
	}

	private void CheckChangedOrder()
	{
	}

	private void OnStatusChanged(ControlFlow.statusType status)
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitForIdleStatus()
	{
		return null;
	}

	private void OnDisable()
	{
	}

	private void OnFinishOrderAnimation()
	{
	}

	public void OnBtnClickFollowingTurn()
	{
	}

	private void OnBtnClickUnitOrderUI(UnitData selectedUnitData)
	{
	}

	private void UpdateUnitInfo(UnitData selectedUnit)
	{
	}

	private int GetUnitStatValue(EnumData.StatType setStatType, UnitData getUnit)
	{
		return 0;
	}

	private void LateUpdateUnitStat()
	{
	}
}
