using System.Collections.Generic;

public class PopupTreasureEnhancePassiveList : CaocaoUIModalPopup
{
	protected Item m_item;

	protected bool m_initialized;

	protected int m_selectedIndex;

	protected UIReuseScrollView m_scroll;

	protected UIReuseGrid m_grid;

	protected Dictionary<int, Dictionary<int, TreasureEnhancePassiveCombinedData>> combinedDataMap;

	protected virtual int itemEnhanceType
	{
		get
		{
			return 0;
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

	public static void Show(Item item)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	protected override void OnDestroy()
	{
	}

	protected virtual void Init()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	protected virtual void UpdateItems(int passiveIndex)
	{
	}

	protected virtual int GetPassiveKey(PassiveList passiveList, Passive passive)
	{
		return 0;
	}

	protected virtual TreasureEnhancePassiveCellData GenerateCellData(int index, int enhancePassiveId, TreasureEnhancePassiveCombinedData combinedData)
	{
		return null;
	}

	protected virtual void OnPassiveClicked(TreasureEnhancePassiveCellData selectedCellData)
	{
	}

	protected List<TreasureEnhancePassive> GetPassiveList(int passiveIndex)
	{
		return null;
	}
}
