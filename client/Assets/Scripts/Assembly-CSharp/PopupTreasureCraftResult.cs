using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PopupTreasureCraftResult : CaocaoUIModalPopup
{
	private enum PopupType
	{
		Normal = 0,
		Retry = 1
	}

	public delegate void Callback();

	private PopupType m_popupType;

	private PanelTreasureCraft.TreasureCraftType m_type;

	private TreasureMix m_treasureMix;

	private List<ulong> m_srls;

	private List<int> m_amounts;

	private int m_selectedEnhancePassiveId;

	private TreasureEnhancePassiveCombinedData m_selectedEnhancePassiveCombinedData;

	private List<ItemUI> m_retryItems;

	private List<EnumData.ItemSlotType> m_exclude;

	private GameObject m_retryBtn;

	private GameObject m_closeBtn;

	private GameObject m_completeObj;

	private UILabelLocalized m_retryBtnLabel;

	private UILabelLocalized m_retryDescLabel;

	private GameObject m_rootObj;

	private Callback m_callback;

	private bool m_selectNewPassive;

	private int m_checkUIClickedPassiveId;

	private int m_checkUIClinkedIndex;

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void ClosePanel()
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

	private void CheckEnhanceFsmFinished()
	{
	}

	private void CheckPassiveChangeFsmFinished()
	{
	}

	private static GameObject GetPrefab(PanelTreasureCraft.TreasureCraftType type)
	{
		return null;
	}

	public static void Show(PanelTreasureCraft.TreasureCraftType type, TreasureMix treasureMix, List<ulong> srls, List<EnumData.ItemSlotType> exclude)
	{
	}

	public static void Show(PanelTreasureCraft.TreasureCraftType type, List<ulong> srls, List<int> amounts)
	{
	}

	public static void Show(PanelTreasureCraft.TreasureCraftType type, ulong srl, int value, Callback callback)
	{
	}

	public static void Show(PanelTreasureCraft.TreasureCraftType type, ulong srl, int value, int selectedEnhancePassiveId, TreasureEnhancePassiveCombinedData selectedEnhancePassiveCombinedData, Callback callback)
	{
	}

	protected override void OnDestroy()
	{
	}

	private void InitRetryUI()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdatePopupType()
	{
	}

	private void UpdateRetryUI()
	{
	}

	private void UpdateResultItems(UIGrid grid)
	{
	}

	private void UpdateResultItem(int index, ulong srl, ItemUI itemUI, UILabelLocalized labelName, GameObject retryObj)
	{
	}

	private void UpdateEnhanceResult()
	{
	}

	private void UpdatePassiveChangeResult()
	{
	}

	private void SetSocketPassiveCheckUI(Transform passiveTranform, EnhancePassiveUI passiveUI, int passiveTriggerId, int uiIndex, bool selectNew)
	{
	}

	private void SetEmptyPassiveCheckUI(Transform passiveTranform, EnhancePassiveUI passiveUI)
	{
	}

	private void UpdateSocketPassiveChangeResult()
	{
	}

	private void ChangedPassiveClicked(bool selectNew, int uiIndex, int clickedPassiveId, bool showTooltip = true)
	{
	}

	private void DecidePassiveChange()
	{
	}

	private void DecideSocketPassiveChange()
	{
	}

	private void AddRetryCheckEvent(UIButton button, ItemUI itemUI)
	{
	}

	private EventDelegate.Parameter AddEventParameter(UnityEngine.Object obj, Type objType)
	{
		return null;
	}

	private void RetryResultEvent(List<ulong> srls)
	{
	}

	public void OnClickRetryBtn()
	{
	}

	private void OnClickItem(ItemUI itemUI)
	{
	}

	private void OnClickRetryCheck(GameObject itemCompose, ItemUI itemUI)
	{
	}

	public void OnClickConfirmSelect()
	{
	}

	private HttpRequest.HttpRequestContext CreateRequestContext()
	{
		return null;
	}

	private void RetryRequestCallback(JsonData response)
	{
	}

	private int GetTotalCostGold()
	{
		return 0;
	}

	private string GetRetryTitleString()
	{
		return null;
	}

	private void UpdateEnhanceResultPayback(Transform root, int keeperAmount, int guaranteeAmount)
	{
	}

	private void UpdateEnhanceResultStat(Transform root, EnumData.StatType type, UserItem userItem, int enhanceDelta)
	{
	}

	private void UpdateEnhanceResultPassive(Transform root, UserItem userItem, int index)
	{
	}

	private void UpdateEnhanceResultGrowPassive(Transform root, UserItem userItem, int delta)
	{
	}
}
