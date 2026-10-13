using System;
using UnityEngine;

[ExecuteInEditMode]
public class UnitTreeItemUI : MonoBehaviour
{
	private static Color ms_cashBGColor;

	private int m_nodeID;

	private bool m_isSelected;

	private bool m_isScrollable;

	public Action<UnitTreeItemUI> OnClickHandler;

	public Action<UnitTreeItemUI> OnDoubleClickHandler;

	public Action<UnitTreeItemUI> OnSelectHandler;

	public Action<UnitTreeItemUI> OnPressHandler;

	public Action<UnitTreeItemUI> OnReleaseHandler;

	private Transform bg;

	private Transform bgCash;

	private UILabelLocalized honorCost;

	private UILabelLocalized silverCost;

	private UILabelLocalized goldCost;

	private UILabelLocalized ticketCost;

	private UILabelLocalized steleCost;

	private UILabelLocalized soulCost;

	private Transform locked;

	private Transform unlocked;

	private Transform purchased;

	private Transform lockIcon;

	private UnitStarWithLabel starLabel;

	private UILabelLocalized levelLabel;

	private UILabelLocalized reinforceLevLabel;

	private UILabelLocalized powerLabel;

	private UITexture unitFace;

	private UILabelLocalized cost;

	private UISprite jobSprtie;

	private UISprite gradeFrame;

	private UILabelLocalized nameLabel;

	public int nodeID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public UserUnitTreeNode userNodeData { get; private set; }

	public UnitTreeNode nodeData { get; private set; }

	public bool isSelected
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isScrollable
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private bool isPurchasedTree { get; set; }

	static UnitTreeItemUI()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public bool IsValid()
	{
		return false;
	}

	public void SetUserTech(UserUnitTreeNode node)
	{
	}

	private void SetNode(int nodeID)
	{
	}

	public void UpdateTech()
	{
	}

	private void OnClick()
	{
	}

	private void OnDoubleClick()
	{
	}

	private void OnSelect()
	{
	}

	private void OnPress()
	{
	}

	private void OnRelease()
	{
	}

	private void OnUnitDeserialzeCallback()
	{
	}

	private void OnTreeNodeDeserialzeCallback()
	{
	}
}
