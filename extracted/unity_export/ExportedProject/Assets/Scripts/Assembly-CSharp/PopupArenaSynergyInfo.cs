using System.Collections.Generic;
using UnityEngine;

public class PopupArenaSynergyInfo : CaocaoUIModalPopup
{
	private ArenaSynergy arenaSynergy;

	private List<UserUnit> units;

	private Transform m_topObj;

	private GameObject m_topDimObj;

	private PrefabInstance m_faceUI;

	private PrefabInstance m_synergyTypeUI;

	private UILabelLocalized m_labelName;

	private UILabelLocalized m_labelDesc;

	private UIGrid m_gridUnit;

	private GameObject m_unitDimObj;

	private UITable m_tableSynergy;

	private UIScrollView m_scrollViewSynergy;

	private GameObject m_synergyDimObj;

	private Transform topObj
	{
		get
		{
			return null;
		}
	}

	private GameObject topDimObj
	{
		get
		{
			return null;
		}
	}

	private PrefabInstance faceUI
	{
		get
		{
			return null;
		}
	}

	private PrefabInstance synergyTypeUI
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

	private UILabelLocalized labelDesc
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridUnit
	{
		get
		{
			return null;
		}
	}

	private GameObject unitDimObj
	{
		get
		{
			return null;
		}
	}

	private UITable tableSynergy
	{
		get
		{
			return null;
		}
	}

	private UIScrollView scrollViewSynergy
	{
		get
		{
			return null;
		}
	}

	private GameObject synergyDimObj
	{
		get
		{
			return null;
		}
	}

	protected override void Awake()
	{
	}

	public static void Show(int arenaSynergyId)
	{
	}

	public static void Show()
	{
	}

	private void UpdateUI()
	{
	}

	protected override void OnDestroy()
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
