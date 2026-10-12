using System;
using System.Collections.Generic;

public class PopupRuneDecomposition : CaocaoUIModalPopup
{
	public UIGrid grid;

	private List<ulong> lstRuneItems;

	private Dictionary<int, int> dicDecomposition;

	private ulong runeSrl;

	private int num;

	private Action onDecomposition;

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
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

	public static void Show(List<ulong> lstRuneItems, Dictionary<int, int> dicDecomposition, Action onDecomposition)
	{
	}

	public static void Show(ulong runeSrl, int num, Action onDecomposition)
	{
	}

	private void UpdateUI()
	{
	}

	public void OnBtnDecomposition()
	{
	}

	public void RequestDecomposeRune(ulong runeSrl)
	{
	}

	public void RequestDecompositionAll(int currentIdx)
	{
	}

	private bool DecompositionGetItemIsMaxByRune(UserRuneItem runeItem)
	{
		return false;
	}

	private bool DecompositionGetItemIsMax(int itemId, int addCount)
	{
		return false;
	}

	private bool DecompositionAllGetItemIsMax()
	{
		return false;
	}

	private bool IsMaxTier(int tier)
	{
		return false;
	}

	protected override void OnDestroy()
	{
	}
}
