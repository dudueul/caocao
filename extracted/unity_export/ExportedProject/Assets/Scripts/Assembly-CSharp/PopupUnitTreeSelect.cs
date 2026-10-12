using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PopupUnitTreeSelect : SelfRegister2UIComponentsMonobehaviour
{
	private List<UILabelLocalized> m_conditions;

	private UILabelLocalized m_unitTreeName;

	private UILabelLocalized m_unitName;

	private Transform m_unitNamesTr;

	private UIReuseGrid m_unitTreeGrid;

	private UIReuseScrollView m_unitTreeScrollView;

	private UIScrollView m_unitTreeHeroScrollView;

	private GameObject m_unlockBtn;

	private GameObject m_silverBtn;

	private GameObject m_goldBtn;

	private GameObject m_honorBtn;

	private Color32 conditionGreen;

	private Color32 conditionRed;

	private Color32 nameWhite;

	private Color32 nameGray;

	private UnitTreeListItem.UnitTreeCellData m_selectedItem;

	private List<UILabelLocalized> m_unitNames;

	private bool m_needToAlign;

	private List<UnitTreeNode> lstSearchNode;

	public void ShowPanel(int unitTreeId)
	{
	}

	public void ClosePanel()
	{
	}

	private void InitUI(int unitTreeId)
	{
	}

	protected override void OnDestroy()
	{
	}

	public void OnSelectUnitTree(UnitTreeListItem.UnitTreeCellData unitTreeCellData)
	{
	}

	private void UpdateGrid(int unitTreeId)
	{
	}

	private void UpdateUI()
	{
	}

	private void SetCondition(UILabelLocalized label, bool didComplete, string conditionStr, params object[] args)
	{
	}

	private void UpdateBtn()
	{
	}

	public void SelectUnitTree()
	{
	}

	public void Unlock()
	{
	}

	public void Bypass()
	{
	}

	private void OnUnitTreeUnlock(JsonData res)
	{
	}

	private void OnUnitTreeBypass(JsonData res)
	{
	}

	public void OnBtnSearch()
	{
	}

	private void GotoTreeNode(UnitTreeNode node)
	{
	}
}
