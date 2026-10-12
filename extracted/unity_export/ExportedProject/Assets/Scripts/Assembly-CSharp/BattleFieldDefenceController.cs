using System;
using System.Collections.Generic;
using Caocao;
using LitJson;

public class BattleFieldDefenceController
{
	private ReconController m_reconController;

	private PanelBattleFieldDefense m_panelBFDefense;

	private PopupQuickBattleDeck m_popupQuickBattleDeck;

	private Dictionary<int, int> m_defenseGroupUnitMaxCount;

	private Dictionary<int, List<BattleFieldDefenseUnit>> m_pickedDefenseUnit;

	private List<int> m_pickedUnitIds;

	public Action onUpdatePickedUnitIdsCallback;

	public Action onUpdatePickedUnitEquippedItemCallback;

	protected EnumData.BattleFieldDefenseMode m_controlMode;

	protected int m_arrangedUnitId;

	public static BattleFieldDefenceController currentController;

	protected UnitData m_moveUnitData;

	protected List<UserUnit> m_availableUnits;

	public ReconController reconController
	{
		get
		{
			return null;
		}
	}

	public PanelBattleFieldDefense panelBFDefense
	{
		get
		{
			return null;
		}
	}

	public PopupQuickBattleDeck popupQuickBattleDeck
	{
		get
		{
			return null;
		}
	}

	public Dictionary<int, int> defenseGroupUnitMax
	{
		get
		{
			return null;
		}
	}

	public Dictionary<int, List<BattleFieldDefenseUnit>> pickedDefenseUnit
	{
		get
		{
			return null;
		}
	}

	public List<int> pickedUnitIds
	{
		get
		{
			return null;
		}
	}

	public EnumData.BattleFieldDefenseMode controlMode
	{
		get
		{
			return EnumData.BattleFieldDefenseMode.None;
		}
	}

	public virtual int arrangedUnitId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	protected virtual string errMsgOtherDefenceGroup
	{
		get
		{
			return null;
		}
	}

	public UnitData moveUnitData
	{
		get
		{
			return null;
		}
	}

	public bool TryGetDefenceGroupUnitMax(int groupId, out int count)
	{
		count = default(int);
		return false;
	}

	public bool TryGetPickedDefenseUnit(int groupId, out List<BattleFieldDefenseUnit> list)
	{
		list = null;
		return false;
	}

	public virtual bool GetPickedDefenseUnitNum(int groupId, out int num)
	{
		num = default(int);
		return false;
	}

	public int CalcServerStoredDepenseGroupPower()
	{
		return 0;
	}

	public virtual bool IsEquipmentWearable(UserUnit unit)
	{
		return false;
	}

	public virtual List<UserUnit> GetUserUnits()
	{
		return null;
	}

	public virtual bool IsOtherDefenceGroupUnit(int unitId)
	{
		return false;
	}

	public virtual UserUnit GetUserUnitFromID(int unitId)
	{
		return null;
	}

	public virtual bool IsHaveUnit(int unitId)
	{
		return false;
	}

	public void Prepare()
	{
	}

	public void Release()
	{
	}

	public virtual void Init()
	{
	}

	public virtual int GetDefUnitMinNum()
	{
		return 0;
	}

	public virtual int GetDefUnitMaxNum()
	{
		return 0;
	}

	public virtual int GetGroupSlotNum(int bfIndex)
	{
		return 0;
	}

	protected virtual BattleFieldDefenseUnit GenerateDefenceUnitInfo(ReconSlot reconSlot, int bfGroup, int bfIdx, int unitId, ulong weapon, ulong armor, ulong kit, int skinItemId)
	{
		return null;
	}

	public virtual void Picked(UserUnit userUnit, ReconSlot reconSlot)
	{
	}

	public virtual void UnPickAll()
	{
	}

	public virtual void UnPick(int unitId)
	{
	}

	public virtual void ReadyArrangeUnit()
	{
	}

	public void CancelReadyArrangeUnit()
	{
	}

	public void CompleteArrangeUnit(UnitData srcUnitData, ReconSlot reconSlot)
	{
	}

	public void CancelArrangeUnit(UnitData srcUnitData)
	{
	}

	public void PullOutUniArrange(UnitData srcUnitData)
	{
	}

	public virtual void MoveUnitArrange(UnitData srcUnitData)
	{
	}

	public virtual void CancelMoveUnitArrange(UnitData srcUnitData)
	{
	}

	public void ClickCloseBtnBFUnitList()
	{
	}

	public virtual void ClickArrangedUnit(UnitData unitData)
	{
	}

	public int GetRemainedSlotCount()
	{
		return 0;
	}

	public bool IsPicked(int unitId)
	{
		return false;
	}

	public virtual int? GetBFIndex(int unitId)
	{
		return null;
	}

	public virtual int GetBFIndex(ReconSlot reconSlot)
	{
		return 0;
	}

	public void ClosePopupUnitSelect()
	{
	}

	public void ClosePopupUnitInfo()
	{
	}

	public virtual void PickInitialUnits()
	{
	}

	public bool PickQuickBattleDeck(List<UserQuickBattleDeckUnit> quickDeckUnits)
	{
		return false;
	}

	public virtual bool TryPick(int unitId)
	{
		return false;
	}

	public int GetPickedUnitAverageLevel()
	{
		return 0;
	}

	public int GetPickedUnitTotalCombatPower()
	{
		return 0;
	}

	public bool IsDefenseUnitMax(int groupId)
	{
		return false;
	}

	public BattleFieldDefenseUnit GetPickedDefenseUnit(int unitId)
	{
		return null;
	}

	private UserUnit GetPickedUnit(int unitId)
	{
		return null;
	}

	protected void ShowFailMessage(string msg, params object[] args)
	{
	}

	protected virtual void RemovePicked(int unitId)
	{
	}

	protected virtual void RemovePicked(UnitData unitData)
	{
	}

	public IntVector2 GetGroupGridPos(int groupIndex, int pos)
	{
		return default(IntVector2);
	}

	public UnitDirection GetDefenseDeckDirection(int groupId)
	{
		return UnitDirection.back;
	}

	public virtual bool IsChangeDefenseDeck()
	{
		return false;
	}

	public virtual string GetChangeEquippedItemDefenseUnit(bool isExiting)
	{
		return null;
	}

	public void UpdatePickedUnitEquippedItem()
	{
	}

	public void MakeInAdvanceDefenseUnits()
	{
	}

	public virtual void SetAutoDefenceDeck(bool reset)
	{
	}

	private void SetAutoDefenseDeck(int groupIndex, int bfGroup, int bfId, bool reset, int tileId)
	{
	}

	private void AutoDefensePosUnit(int groupIndex, int unitPos, int unitType, bool reset, int tileId)
	{
	}

	private void GenerateBFDefenceUnit(int groupIndex, int unitPos, UserUnit userUnit, bool reset)
	{
	}

	public void RemoveEventHanlder()
	{
	}

	public void AddEventHandler()
	{
	}

	private void ResponsePickUnitItemEquip(JsonData response)
	{
	}

	public virtual void WriteDefenseUnitDecks(List<object> args)
	{
	}

	protected virtual void WriteDefenseUnits(List<object> saveObjs, string groupName, int groupNumber)
	{
	}
}
