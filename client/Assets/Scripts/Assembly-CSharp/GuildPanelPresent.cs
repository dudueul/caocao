using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class GuildPanelPresent : PanelSelfRegister2UIComponentsMonobehaviour
{
	private enum HistoryType
	{
		All = 0,
		Gain = 1,
		Present = 2
	}

	private enum InvenFilterType
	{
		All = 0,
		Goods = 1,
		Tools = 2,
		Items = 3
	}

	private enum RequestSequence
	{
		None = 0,
		Storage = 1,
		StorageDone = 2,
		StorageLog = 3,
		StorageLogDone = 4,
		PresentLog = 5,
		PresentLogDone = 6,
		PresentUserHistory = 7,
		PresentUserHistoryDone = 8,
		End = 9
	}

	public CaocaoTabButtons tabButtons;

	public UIReuseGrid gridHistory;

	public UIReuseGrid gridInventory;

	public GameObject objFilterList;

	public int goodsStepAmount;

	public UILabelLocalized sortLabel;

	private HistoryType curHistory;

	private InvenFilterType curFilter;

	private Dictionary<int, GuildPresentHistory> m_dicLogPresent;

	private Dictionary<int, GuildPresentHistory> m_dicLogStorage;

	private List<GuildPresentHistory> m_listLogAll;

	private TooltipContentsGenerator tooltip;

	private bool isGoodSelected;

	private int selectedItemId;

	private int selectedItemAmount;

	private RequestSequence curRequest;

	private bool updatedPresentHistory;

	private int updatedMemberNameNum;

	private int m_presentHistoryLastSrl;

	private int m_storagetHistoryLastSrl;

	public override void ShowPanel()
	{
	}

	[DebuggerHidden]
	private IEnumerator Request()
	{
		return null;
	}

	private void PresentNextPageLoading()
	{
	}

	public override void ClosePanel()
	{
	}

	public static void Show()
	{
	}

	private void OnUpdateStorageLog(int lastSrl)
	{
	}

	private void OnUpdatePresentLog(int lastSrl)
	{
	}

	private void OnUpdateStorage()
	{
	}

	private void OnMemberPresentHistory()
	{
	}

	private void OnGuildError(string error, ref bool handled)
	{
	}

	private void UpdateHistoryUI()
	{
	}

	private void UpdateHistoryMemberNames()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitForMemberName()
	{
		return null;
	}

	private void UpdateStorageUI()
	{
	}

	private void OnSelectedInvenItem(int index, bool isGood)
	{
	}

	private void OnSelectedInven(int index)
	{
	}

	private void OnPresentClicked()
	{
	}

	private void OnConditionClicked()
	{
	}

	private void OnClosePresentPopup(bool presented)
	{
	}

	private void OnHistoryTabChanged(CaocaoButton button)
	{
	}

	public void OnFilterClicked()
	{
	}

	public void OnFilterChanged()
	{
	}

	protected override void OnDestroy()
	{
	}
}
