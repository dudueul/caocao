using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PanelMissions : CaocaoUIModalPopup
{
	private enum CATEGORY
	{
		epic = 0,
		normal_special = 1,
		normals = 2,
		newbie = 6
	}

	private Action<int> onSelectTab__BackingField;

	private Action onNewTag__BackingField;

	private Action<int> onSelectEpic__BackingField;

	private int m_selectedTabId;

	private bool m_changedTab;

	private int m_selectedEpicId;

	private int m_lastEpicId;

	private int m_currentEpicPage;

	private int m_maxEpicPage;

	private int m_requestCompleteId;

	private const int MAX_EPIC_LISTCOUNT = 10;

	private int m_befUserRank;

	private int m_befSeasonPassLv;

	private UIGrid m_gridTab;

	private UIGrid m_gridEpic;

	private UIGrid m_gridSpecial;

	private UIGrid m_gridNormal;

	private PopupMissionComplete completePopup;

	public int selectedTabId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	private UIGrid gridTab
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridEpic
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridSpecial
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridNormal
	{
		get
		{
			return null;
		}
	}

	private event Action<int> onSelectTab
	{
		add
		{
		}
		remove
		{
		}
	}

	private event Action onNewTag
	{
		add
		{
		}
		remove
		{
		}
	}

	private event Action<int> onSelectEpic
	{
		add
		{
		}
		remove
		{
		}
	}

	public static void Show()
	{
	}

	protected override void Awake()
	{
	}

	private void Start()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void OnClickTab(int id)
	{
	}

	private void OnClickEpicMission(int id)
	{
	}

	public void OnClickComplete(int missionId)
	{
	}

	private void OnCompleteMissionCallback(JsonData response)
	{
	}

	private void OnClickEpicPagePrev()
	{
	}

	private void OnClickEpicPageNext()
	{
	}

	public void OnClickEpicMove()
	{
	}

	public void OnClickEpicComplete()
	{
	}

	private void SetDefaultEpicMissionPage()
	{
	}

	private void SetEpicMissionPageButton()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateTab()
	{
	}

	private void UpdatePage(int id)
	{
	}

	private void UpdatePageDetail(Transform tfPage, UIGrid grid, CommonMissiontitle missionTitle, bool isSelect, bool special, bool existMissionOnly)
	{
	}

	private void UpdateEpicPageDetail(Transform tfPage, CommonMissiontitle missionTitle, bool isSelect)
	{
	}

	private void UpdateSelectedEpicMission(int id)
	{
	}

	private void UpdateComplete(int missionId, List<RewardInfo> rewardSet, bool showDesc)
	{
	}

	private void AddTabButton(CommonMissiontitle title)
	{
	}

	private void AddEpicItem(CommonMission mission, int index)
	{
	}

	private void AddNormalSpecial(int idx, CommonMission mission)
	{
	}

	private void AddNormal(int idx, CommonMission mission)
	{
	}

	private void UpdateNormal(int idx, CommonMission mission)
	{
	}

	private void ClearGrid(UIGrid grid)
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
