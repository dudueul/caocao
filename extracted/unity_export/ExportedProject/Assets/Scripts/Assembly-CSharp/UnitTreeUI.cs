using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class UnitTreeUI : PanelSelfRegister2UIComponentsMonobehaviour
{
	private int _id;

	private int defaultUnitTreeID;

	private float unitDescUIWidth;

	private bool m_rotating;

	private bool m_willCloseUnitTreeUI;

	public static Action<bool> OnToggledUnitTree;

	private bool m_started;

	private RaidStuffUI m_raidSteleUI;

	private RaidStuffUI m_raidSoulUI;

	private Dictionary<int, List<UnitTreeItem>> treeID2Nodes;

	private Dictionary<int, UnitTreeItem> id2Nodes;

	private AudioSource m_playingAudioSource;

	private AudioClip m_lastAudioClip;

	public int id
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	private Transform m_backBtnTitle
	{
		get
		{
			return null;
		}
	}

	private GameObject m_content
	{
		get
		{
			return null;
		}
	}

	private GameObject m_staticBg
	{
		get
		{
			return null;
		}
	}

	private Transform m_fx
	{
		get
		{
			return null;
		}
	}

	private Transform m_se
	{
		get
		{
			return null;
		}
	}

	private PopupUnitTreeSelect m_popupUnitTreeSelect
	{
		get
		{
			return null;
		}
	}

	private void Start()
	{
	}

	public override void OnInit()
	{
	}

	public override void ShowPanel()
	{
	}

	public void ShowHeroListPopup()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void ShowContent(bool active)
	{
	}

	public void OnClickBackBtn()
	{
	}

	public void OnCloseUnitTreePopup()
	{
	}

	public override void ClosePanel()
	{
	}

	public void ParseTableAndRearrange()
	{
	}

	public void DeployTree()
	{
	}

	private void DeployNode()
	{
	}

	private void DeployLine()
	{
	}

	private void RotatePanel()
	{
	}

	[DebuggerHidden]
	private IEnumerator StartRotateTimer(float time)
	{
		return null;
	}

	private string GetLineSpriteName(bool isAndCondition, UserUnitTreeNode precedenceUserUnitTreeNode)
	{
		return null;
	}

	private void OnClickTreeNode(UnitTreeItemUI item)
	{
	}

	private void FocusItem(UnitTreeItemUI item, bool withOverlay)
	{
	}

	private void OnClickPurchaseTreeNode(int nodeID)
	{
	}

	private void OnClickUnlockTreeNode(int nodeID)
	{
	}

	private void OnClickUnlockAndPurchaseTreeNode(int nodeID)
	{
	}

	private void OnClickUnlockAndPurchaseTreeNodeByTicket(int nodeID)
	{
	}

	public void ShowUnitTreeList()
	{
	}

	private void CheckRaidGoodsState()
	{
	}

	public void UnitTreeListItemClicked(int unitTreeID)
	{
	}

	public void PopAllUnitList()
	{
	}

	private UnitDescOnTree GetUnitDesc()
	{
		return null;
	}

	public void HideUnitDesc()
	{
	}

	private void UpdateUnitDesc(UnitTreeItemUI item)
	{
	}

	public void RecoverAllUnitEnergies()
	{
	}

	private void OnUnitTreeNodePurchaseResult(JsonData res)
	{
	}

	private void OnUnitTreeNodeUnlockResult(JsonData res)
	{
	}

	private void OnUnitTreeNodeUnlockAndPurchaseResult(JsonData res)
	{
	}

	private void OnUnitTreeNodeUnlockAndPurchaseByTicketResult(JsonData res)
	{
	}

	private void OnDeserializedUnitTreeNodes()
	{
	}

	public void FocusUnitTreeNode(int unitTreeNodeId)
	{
	}
}
