using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class PopupArenaStageInfo : CaocaoUIModalPopup
{
	public enum TabType
	{
		Current = 0,
		All = 1
	}

	private TabType tab;

	private UserArenaLevel userArenaLevel;

	private ArenaLevel pdArenaLevel;

	private ArenaStage pdStage;

	private ArenaNPCFormation pdNpcFormation;

	private bool prevPauseState;

	private bool initCurStage;

	private Dictionary<int, int> faceIdx2NpcIdx;

	private bool initAllStage;

	private CaocaoTabButtons m_tabButton;

	private Transform m_objCurStage;

	private UILabelLocalized m_labelStageName;

	private UILabelLocalized m_labelLevelName;

	private UILabelLocalized m_labelStageDesc;

	private MissionInfoUI m_missionDesc;

	private UIGrid m_gridMapInfo;

	private UILabelLocalized m_labelTurn;

	private Transform m_objTileInfo;

	private Transform[] m_objWeathers;

	private UITable m_gridPassives;

	private GameObject m_objDimPassive;

	private Transform m_objNpcPosition;

	private Transform m_objAllStage;

	private UIReuseGrid m_gridAllStage;

	private CaocaoTabButtons tabButton
	{
		get
		{
			return null;
		}
	}

	private Transform objCurStage
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelStageName
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelLevelName
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelStageDesc
	{
		get
		{
			return null;
		}
	}

	private MissionInfoUI missionDesc
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridMapInfo
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelTurn
	{
		get
		{
			return null;
		}
	}

	private Transform objTileInfo
	{
		get
		{
			return null;
		}
	}

	private Transform[] objWeathers
	{
		get
		{
			return null;
		}
	}

	private UITable gridPassives
	{
		get
		{
			return null;
		}
	}

	private GameObject objDimPassive
	{
		get
		{
			return null;
		}
	}

	private Transform objNpcPosition
	{
		get
		{
			return null;
		}
	}

	private Transform objAllStage
	{
		get
		{
			return null;
		}
	}

	private UIReuseGrid gridAllStage
	{
		get
		{
			return null;
		}
	}

	public static void Show(TabType tabType = TabType.Current)
	{
	}

	private void InitUI()
	{
	}

	private void OnTabChanged(CaocaoButton btn)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateCurrentStageUI()
	{
	}

	[DebuggerHidden]
	private IEnumerator DelayRepositionTable(UITable table)
	{
		return null;
	}

	public void OnFaceButton()
	{
	}

	private void UpdateAllStageUI()
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
