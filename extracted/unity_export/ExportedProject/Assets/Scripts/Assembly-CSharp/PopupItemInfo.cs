using UnityEngine;

public class PopupItemInfo : CaocaoUIModalPopup
{
	protected delegate void EquipItemFunc(UserUnit userUnit, ulong itemSrl);

	protected UserUnit m_userUnit;

	protected UserItem m_equippedItem;

	protected UserItem m_selectedItem;

	protected int m_slotType;

	public PrefabInstance m_itemInst;

	public UILabelLocalized m_name;

	public UILabelLocalized m_type;

	public UILabelLocalized m_count;

	public UILabelLocalized m_level;

	public UILabelLocalized m_countLabel;

	private ItemStatListItem m_statListItem;

	private ItemSpActionListItem m_spActionListItem;

	private ItemJobTypeListItem m_jobTypeListItem;

	private ItemDescListItem m_descListItem;

	public GameObject m_reinforceBtn;

	public GameObject m_takeOffBtn;

	public GameObject m_equipBtn;

	public UIScrollView m_scrollView;

	protected bool m_hideBottomArea;

	public GameObject m_bottomBG;

	protected EquipItemFunc equipItemFunc;

	protected int m_quickSlotDeckId;

	public UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public virtual UserItem equippedItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public virtual UserItem selectedItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public RewardInfo rewardInfo { get; set; }

	protected IUserDataBase userData
	{
		get
		{
			return null;
		}
	}

	public static void ShowPopup(UserUnit userUnit, EnumData.ItemSlotType slotType)
	{
	}

	public static void ShowPopup(UserUnit userUnit, EnumData.ItemSlotType slotType, bool hideBottomArea)
	{
	}

	public static void ShowPopup(RewardInfo rewardInfo, int depth)
	{
	}

	private void OnDisable()
	{
	}

	protected override void Awake()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	protected virtual void InitUI()
	{
	}

	protected void ActivateReinforceBtn()
	{
	}

	protected void ActivateEqiupBtn()
	{
	}

	protected virtual void UpdateUI()
	{
	}

	private void OverrideCount()
	{
	}

	public void ShowItemReinforcePopup()
	{
	}

	public void TakeOffItem()
	{
	}

	public virtual void TakeOffSelectedItem()
	{
	}

	public void ShowItemListPopup()
	{
	}

	protected void EquipItem(UserUnit userUnit, ulong itemSrl)
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
}
