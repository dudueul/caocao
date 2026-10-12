using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

[ExecuteInEditMode]
public class ItemUI : MonoBehaviour
{
	private readonly Color32[] m_starColors;

	private readonly Color32[] m_starColorsJpn;

	private GameObject m_deckNumberBgGo;

	private UILabelLocalized m_centerDesc;

	private UILabelLocalized m_deckNumberLabel;

	private GameObject m_quickSlotNumberBgGo;

	private UILabelLocalized m_quickSlotNumberLabel;

	private Transform m_treasureLockIcon;

	private UserItem m_userItem;

	private UserUnit m_equippedUserUnit;

	private bool m_showAvailable;

	private bool m_arenaItem;

	private Item m_itemData;

	private int m_level;

	private int m_amount;

	private EnumData.ItemSlotType m_slotType;

	private bool m_isSelected;

	private bool m_isScrollable;

	private bool m_isEquipped;

	private string m_equippedSpriteName;

	private string m_equippedLabelText;

	private bool m_isEquippedQuickSlot;

	private bool m_checkIsOwned;

	private bool m_checkIsDisable;

	private bool m_showDesc;

	private bool m_isCountable;

	public bool countEnabled;

	private int m_selectedCount;

	private bool m_isTreasure;

	private bool m_isOwned;

	private GameObject m_selectedNum;

	private UILabelLocalized m_selectedNumLabel;

	private UISprite m_selectedSprite;

	private GameObject m_equipped;

	private GameObject m_pocket;

	private UIGrid m_starGrid;

	public RewardInfo rewardInfo;

	private EnumData.ItemType m_itemType;

	public Action<ItemUI> OnClickHandler;

	public Action<ItemUI> OnDoubleClickHandler;

	public Action<ItemUI> OnSelectHandler;

	public Action<ItemUI> OnPressHandler;

	public Action<ItemUI> OnReleaseHandler;

	public Action<ItemUI> OnClickNoCheckValidHandler;

	private bool showDeckNumber;

	private bool showEquippedInfo;

	public GameObject deckNumberBgGo
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized centerDesc
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized deckNumberLabel
	{
		get
		{
			return null;
		}
	}

	public GameObject quickSlotNumberBgGo
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized quickSlotNumberLabel
	{
		get
		{
			return null;
		}
	}

	public Transform treasureLockIcon
	{
		get
		{
			return null;
		}
	}

	public UserItem userItem
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public UserUnit equippedUserUnit
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public bool showAvailable
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool arenaItem
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public Action itemUpdatedCallback { get; set; }

	public Item itemData
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public TimeBox timeBoxData { get; set; }

	public EnumData.ItemSlotType slotType
	{
		get
		{
			return (EnumData.ItemSlotType)0;
		}
		set
		{
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

	public bool isScrollable
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isEquipped
	{
		get
		{
			return false;
		}
		private set
		{
		}
	}

	public string equippedSpriteName
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public string equippedLabelText
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public bool isEquippedQuickSlot
	{
		get
		{
			return false;
		}
	}

	public bool checkIsOwned
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool checkIsDisable
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool showDesc
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool IsCountable
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public int selectedCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool isTreasure
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isOwned
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public GameObject selectedNum
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized selectedNumLabel
	{
		get
		{
			return null;
		}
	}

	public UISprite selectedSprite
	{
		get
		{
			return null;
		}
	}

	private GameObject equipped
	{
		get
		{
			return null;
		}
	}

	public GameObject pocket
	{
		get
		{
			return null;
		}
	}

	public UIGrid starGrid
	{
		get
		{
			return null;
		}
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	public void SetDefaultEquip(EnumData.ItemType itemType)
	{
	}

	public void SetDefaultEquipWithUnit(EnumData.ItemType itemType, UserUnit userUnit)
	{
	}

	public void SetUserItemWithFlicker(UserItem item, UserUnit userUnit = null)
	{
	}

	public void SetUserItem(UserItem item)
	{
	}

	public void SetUserItemWithUnit(UserItem item, UserUnit userUnit)
	{
	}

	public void SetItem(int itemID)
	{
	}

	public void SetItem(int itemID, int amount)
	{
	}

	public void SetReward(RewardInfo rewardInfo)
	{
	}

	public void SetColor(Color color)
	{
	}

	private void ItemUpdated()
	{
	}

	private void UpdateItem()
	{
	}

	private string GetIconFrameName()
	{
		return null;
	}

	private void UpdateIconFrame()
	{
	}

	public void UpdateAvailable()
	{
	}

	private void UpdateDesc()
	{
	}

	private void UpdateDescEnhance()
	{
	}

	private void UpdateDescExp()
	{
	}

	private void UpdateTreasureLock()
	{
	}

	public void OnClick()
	{
	}

	public void OnDoubleClick()
	{
	}

	public void OnSelect()
	{
	}

	public void OnPress()
	{
	}

	public void OnRelease()
	{
	}

	public void OnClickNoCheckValid()
	{
	}

	public void SetSelectedColor(Color color)
	{
	}

	[DebuggerHidden]
	private IEnumerator _ShowEquipEffect(UserItem item)
	{
		return null;
	}

	private void ClearEquipEffect()
	{
	}

	private void UpdateStar()
	{
	}

	public void HideEquippedInfo()
	{
	}

	private void UpdateEquipped()
	{
	}

	public void SetShowEquippedInfo(UserUnit userUnit, bool showEquippedInfo, bool showDeckNumber)
	{
	}

	public void UpdateEquippedInfo(UserUnit userUnit, bool showDeckNumber)
	{
	}

	private void ShowEquippedInfoBattleFieldMode(bool showDeckNumber)
	{
	}

	public void ShowEquippedDeckNumber()
	{
	}

	private bool IsPlayerUnit()
	{
		return false;
	}

	private void UpdatePocketConsumableNum()
	{
	}

	private void UpdateDeckNumber(UserUnit userUnit)
	{
	}

	public void HideQuickSlotInfo()
	{
	}

	private void EquippedUnitUpdated()
	{
	}

	public void ShowQuickSlotInfo(UserUnit userUnit)
	{
	}

	private string GetBattleFiledNameEquippedItem(ulong itemSrl)
	{
		return null;
	}
}
