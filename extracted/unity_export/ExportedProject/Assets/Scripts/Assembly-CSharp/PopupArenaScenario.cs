using System;
using System.Collections.Generic;
using UnityEngine;

public class PopupArenaScenario : MonoBehaviour
{
	private const string SEL_SCENARIO_PREFS_KEY = "ARENA_SELECTED_SCENARIO";

	private int selectedScenario;

	private Dictionary<int, Transform> gridItems;

	private Action callback;

	private bool initiated;

	private Vector3 scrollViewInitPosition;

	private UIScrollView m_scrollViewScenario;

	private UIGrid m_gridScenario;

	private Transform m_detailObj;

	private UILabelLocalized m_labelName;

	private Transform m_unitList;

	private UILabelLocalized m_labelDesc;

	private GameObject m_btnConfirm;

	private GameObject m_btnPurchase;

	private UILabelLocalized m_labelPurchaseCost;

	private Transform m_stateObj;

	private UILabelLocalized m_labelClear;

	private UILabelLocalized m_labelStageClear;

	private UILabelLocalized m_labelStamp;

	private GameObject m_clearObj;

	private GameObject m_perfectObj;

	private UIScrollView scrollViewScenario
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridScenario
	{
		get
		{
			return null;
		}
	}

	private Transform detailObj
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelName
	{
		get
		{
			return null;
		}
	}

	private Transform unitList
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelDesc
	{
		get
		{
			return null;
		}
	}

	private GameObject btnConfirm
	{
		get
		{
			return null;
		}
	}

	private GameObject btnPurchase
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelPurchaseCost
	{
		get
		{
			return null;
		}
	}

	private Transform stateObj
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelClear
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelStageClear
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelStamp
	{
		get
		{
			return null;
		}
	}

	private GameObject clearObj
	{
		get
		{
			return null;
		}
	}

	private GameObject perfectObj
	{
		get
		{
			return null;
		}
	}

	public void Show(Action callback)
	{
	}

	private void InitGrid()
	{
	}

	private void ScrollToItem(int scenarioId)
	{
	}

	private void UpdateScenarioUI(ArenaScenario pdScenario, Transform item)
	{
	}

	private void SelectItem(int scenarioId)
	{
	}

	private void UpdateRightUI()
	{
	}

	public void OnPurchaseButton()
	{
	}

	public void OnStartArenaButton()
	{
	}

	private void OnDestroy()
	{
	}
}
