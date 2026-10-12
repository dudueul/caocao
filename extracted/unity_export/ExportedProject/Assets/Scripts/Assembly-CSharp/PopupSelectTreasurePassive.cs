using System;

public class PopupSelectTreasurePassive : PopupTreasureEnhancePassiveList
{
	private UILabelLocalized m_labelDesc;

	private UILabelLocalized m_labelCost;

	private UserItem m_userItem;

	private int m_selectedDataIndex;

	private Action m_completedCallback;

	protected override int itemEnhanceType
	{
		get
		{
			return 0;
		}
	}

	public static void Show(UserItem selectedItem, int passiveIndex, Action completedCallback)
	{
	}

	protected override void Init()
	{
	}

	private void UpdateUI()
	{
	}

	protected override int GetPassiveKey(PassiveList passiveList, Passive passive)
	{
		return 0;
	}

	protected override TreasureEnhancePassiveCellData GenerateCellData(int index, int enhancePassiveId, TreasureEnhancePassiveCombinedData combinedData)
	{
		return null;
	}

	public void OnSelectButton()
	{
	}

	private bool CheckSamePassive(int enhancePassiveId)
	{
		return false;
	}

	protected override void OnDestroy()
	{
	}

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
}
