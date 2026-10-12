using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class PopupItemCraft : CaocaoUIModalPopup
{
	private enum AutoCraftingActionStatus
	{
		Idle = 0,
		Crafting = 1,
		SelectingItem = 2,
		Done = 3
	}

	private Item m_itemData;

	private Item m_selectedItemData;

	private PrefabInstance m_output;

	private UILabelLocalized m_outputName;

	private PrefabInstance[] m_materials;

	private UILabelLocalized[] m_materialNames;

	private UILabelLocalized[] m_materialCounts;

	private UISprite m_lineTop;

	private UISprite[] m_lines;

	private List<ItemHistoryListItem> m_histories;

	private UIGrid m_historyGrid;

	private UIScrollView m_historyScrollView;

	private int m_cost;

	private UILabelLocalized m_craftCost;

	private UILabelLocalized m_autoCraftLabel;

	private UIButton m_autoCraftButton;

	private List<Vector3> m_materialsPosBackup;

	private bool m_enoughIngredients;

	private int m_totalCost;

	private int m_craftCount;

	private Dictionary<int, int> m_craftItems;

	private PressableButton minusBtn;

	private PressableButton plusBtn;

	private int amount;

	private const int min_amount = 1;

	private const int max_amount = 999;

	private bool m_autoCraft;

	private Coroutine m_autoCraftCoroutine;

	private Item m_autoCraftItem;

	private AutoCraftingActionStatus m_autoCraftingActionStatus;

	protected override void Awake()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public static void Show(Item itemData)
	{
	}

	private void SetData(Item itemData)
	{
	}

	private void SelectItem(Item itemData)
	{
	}

	private void InitUi()
	{
	}

	private void UpdateUi()
	{
	}

	private void InitHistory()
	{
	}

	private void ClearHistory()
	{
	}

	private void AddHistory(Item itemData)
	{
	}

	private void UpdateHistory()
	{
	}

	public void OnBack()
	{
	}

	private void ClickDestItem(ItemUI itemUi)
	{
	}

	private void ClickMaterial(ItemUI itemUi)
	{
	}

	private void CraftItem()
	{
	}

	private HttpRequest.HttpRequestContext CreateCraftRequestContext(int itemId)
	{
		return null;
	}

	private void CraftRequestCallback(JsonData response)
	{
	}

	private void OnSuccessAutoCraftRequest()
	{
	}

	[DebuggerHidden]
	private IEnumerator OnSuccessAutoCraftRequestCoroutine()
	{
		return null;
	}

	private void StopAutoCraft()
	{
	}

	public void CraftAll()
	{
	}

	private void UpdateAutoCraftButton(bool enable, string label)
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateAutoCraftButtonCoroutine(string label, float delay)
	{
		return null;
	}

	private void PopupAutoCraftConfirm()
	{
	}

	[DebuggerHidden]
	private IEnumerator CraftAllCoroutine()
	{
		return null;
	}

	private AutoCraftingActionStatus DoAction()
	{
		return AutoCraftingActionStatus.Idle;
	}

	public void CraftImmediately()
	{
	}

	private void PopupImmediatelyCraftConfirm()
	{
	}

	private bool CheckEnoughRawMaterialAll(Mix mix, int amount)
	{
		return false;
	}

	[DebuggerHidden]
	private IEnumerator ImmediatelyCraftCoroutine(int itemId, int cost)
	{
		return null;
	}

	private void ImmediatelyCraftCallback(JsonData response)
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowCraftEffect()
	{
		return null;
	}

	private void OnPlusAmount(int count)
	{
	}

	private void OnMinusAmount(int count)
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

	protected override void OnDestroy()
	{
	}
}
