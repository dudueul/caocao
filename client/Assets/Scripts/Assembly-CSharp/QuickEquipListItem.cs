using System;
using LitJson;
using UnityEngine;

public class QuickEquipListItem : MonoBehaviour
{
	public UILabelLocalized labelDeckNum;

	public UILabelLocalized labelName;

	public UIInput inputName;

	public EquipUI equipUI;

	public UIButton btnEquip;

	public UIButton btnBuy;

	public UILabelLocalized labelCost;

	public GameObject objDim;

	private UserTreasureQuickSlot quickSlot;

	private UserUnit userUnit;

	private bool editingName;

	private string origName;

	private bool needToCancelInputName;

	private Action onUpdateSlot;

	private int cost;

	public int deckId { get; private set; }

	public Action<QuickEquipListItem> equipCallback { get; set; }

	public void UpdateUI(bool open, int deckId, UserTreasureQuickSlot quickSlot, UserUnit userUnit, Action onUpdateSlot)
	{
	}

	public void SetCost(int cost)
	{
	}

	private void OnClickItem(ItemUI itemUI)
	{
	}

	public void OnSubmitName()
	{
	}

	public void OnStartEditName()
	{
	}

	public void CancelEditName()
	{
	}

	private void Update()
	{
	}

	public void OnBtnBuy()
	{
	}

	private void OnBoughtSlot(JsonData response)
	{
	}

	public void OnBtnEquip()
	{
	}

	private void OnDestroy()
	{
	}
}
