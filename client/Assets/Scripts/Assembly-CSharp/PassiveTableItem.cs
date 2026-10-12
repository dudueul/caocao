using LitJson;
using UnityEngine;

public class PassiveTableItem : MonoBehaviour
{
	public enum PassiveType
	{
		LordEffect = 0,
		MayorEffect = 1,
		TreasurePassive = 2,
		TroopsPassive = 3,
		GeneralPassive = 4,
		ArenaPassive = 5
	}

	private UserUnit m_userUnit;

	private PassiveType m_type;

	private LordEffect m_lordEffect;

	private MayorEffect m_mayorEffect;

	private Passive m_passive;

	private int m_passiveValue;

	private int m_triggerRateValue;

	private int m_conditionVal1;

	private int m_conditionVal2;

	public bool isAcquired;

	private UserItem m_treasureItem;

	private EnumData.ItemSlotType m_slotType;

	private EnumData.ItemType m_itemType;

	private int m_index;

	private int m_passiveIndex;

	private bool m_isSelected;

	public bool isNull
	{
		get
		{
			return false;
		}
	}

	public bool willHIde
	{
		get
		{
			return false;
		}
	}

	private UILabelLocalized m_name
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_level
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_desc
	{
		get
		{
			return null;
		}
	}

	private LordEffectUI m_lordEffectUI
	{
		get
		{
			return null;
		}
	}

	private MayorEffectUI m_mayorEffectUI
	{
		get
		{
			return null;
		}
	}

	private PassiveUI m_passiveUI
	{
		get
		{
			return null;
		}
	}

	private GameObject m_deactivated
	{
		get
		{
			return null;
		}
	}

	private GameObject m_changeBtn
	{
		get
		{
			return null;
		}
	}

	private GameObject m_descBtn
	{
		get
		{
			return null;
		}
	}

	private GameObject m_selected
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_changeBtnLabel
	{
		get
		{
			return null;
		}
	}

	public bool isSelected
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public void SetLordEffect(UserUnit userUnit)
	{
	}

	public void SetMayorEffect(UserUnit userUnit)
	{
	}

	public void SetTreasurePassive(UserUnit userUnit, UserItem treasureItem, EnumData.ItemSlotType slotType, EnumData.ItemType itemType, int passiveIndex)
	{
	}

	public void SetTroopsPassive(UserUnit userUnit, Passive passive, int passiveValue, bool acquired)
	{
	}

	public void SetGeneralPassive(UserUnit userUnit, PassiveList passiveList, int index)
	{
	}

	public void SetArenaGeneralPassive(UserUnit userUnit, int passiveId, int val, int triggerRateValue, int conditionVal1, int conditionVal2, int index, bool canTakeOff = true)
	{
	}

	private void UpdateLordEffectUI()
	{
	}

	private void UpdateMayorEffectUI()
	{
	}

	private void UpdateTreasurePassiveUI()
	{
	}

	private void UpdateTroopsPassiveUI()
	{
	}

	private void UpdateGeneralPassiveUI(bool isUnitPassive, bool canTakeOff = true)
	{
	}

	private void SetGeneralPassiveInfoUI()
	{
	}

	private void OnClickPassive(SpecialStateUI specialStateUI)
	{
	}

	public void ChangeGeneralEffect()
	{
	}

	public void ChangeArenaGeneralEffect()
	{
	}

	private void ShowPassiveEquipPopup(JsonData jsonData)
	{
	}

	private void SetNeedJobLevelBtn(bool activate, int needJobLevel = 0)
	{
	}
}
