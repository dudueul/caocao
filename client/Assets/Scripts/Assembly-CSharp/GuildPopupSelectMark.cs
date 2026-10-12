using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class GuildPopupSelectMark : CaocaoUIModalPopup
{
	public UIReuseGrid grid;

	public UITexture curGuildMark;

	public UITexture curSmallMark;

	public GameObject btnConfirmCost;

	public GameObject btnConfirmFree;

	public UILabelLocalized labelCost;

	private Dictionary<GuildUIMarkListItem, int> dicLoadQueue;

	private Dictionary<int, Texture> loadedMarks;

	private int markTexIndex;

	private Action<int> onClose;

	private int selectedIdx;

	private bool confirmChange;

	private bool needCost;

	private int origMarkIndex;

	private int origGuildWarRank;

	private int origRankingMarkIndex;

	private int freeMarkTexIndex;

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

	protected override void Awake()
	{
	}

	public static void Show(int markTexIndex, bool needCost, int origGuildWarRank, int freeMarkTexIndex, Action<int> onClose)
	{
	}

	public void SelectItem(int idx)
	{
	}

	public void UpdateCostButton()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateCurrentMarkUI()
	{
	}

	public void OnChangeClicked()
	{
	}

	private void OnChangedMark(JsonData response)
	{
	}

	public Texture GetMarkTexture(GuildUIMarkListItem listItem, int index)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator LoadTextureProcess()
	{
		return null;
	}

	protected override void OnDestroy()
	{
	}
}
