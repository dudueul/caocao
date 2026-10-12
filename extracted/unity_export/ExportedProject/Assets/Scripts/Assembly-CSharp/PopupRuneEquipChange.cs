using System;
using LitJson;
using UnityEngine;

public class PopupRuneEquipChange : CaocaoUIModalPopup
{
	public UILabelLocalized m_title;

	public UILabelLocalized m_subTitle;

	public UIButton m_equipRune;

	public UIButton m_cancel;

	public RuneSlotUI m_source;

	public RuneSlotUI m_target;

	public UILabelLocalized m_restorePercentLabel;

	public GameObject m_soul;

	public GameObject m_returnItem;

	public UIToggle m_returnSelect;

	public GameObject m_destroy;

	public GameObject m_restore;

	public GameObject m_targetState;

	public RuneEquipChangeSelecteStele selectSteleUI;

	private EnumData.RuneEquipType m_runeEquipType;

	private UserUnit m_userUnit;

	private UserRuneItem m_userRuneItem;

	private UserRuneSlot m_sourceSlot;

	private RuneItemSlot m_sourceItem;

	private ItemUI m_returnItemUI;

	private Action m_callback;

	private bool m_reproduct;

	public static PopupRuneEquipChange instance;

	public bool reproduct
	{
		get
		{
			return false;
		}
		private set
		{
		}
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

	public static void Show(UserUnit userUnit, int runeSlotIndex, int runeSlotNum, UserRuneItem userRuneItem, EnumData.RuneEquipType runeEquipType, Action callback = null)
	{
	}

	private void AddOnClickHandler()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUIByRuneEquipType()
	{
	}

	private void UpdateReturnItemUI()
	{
	}

	private void UpdateSourceRuneSlotUI()
	{
	}

	private void UpdateTargetRuneSlotUI()
	{
	}

	private void UpdateRestorePercentUI(bool active, float restoreRatio = 0f)
	{
	}

	private void UpdateSelectSteleUI()
	{
	}

	private void UpdateSourceStateObject()
	{
	}

	private void ChangeRune()
	{
	}

	private void OnEquipRune(JsonData response)
	{
	}

	public void OnSelectSteleStateChanged(EnumData.RaidStuffType befSelectedStuff, EnumData.RaidStuffType selectedStuff)
	{
	}
}
