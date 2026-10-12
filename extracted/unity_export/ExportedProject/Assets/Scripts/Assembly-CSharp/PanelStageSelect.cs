using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class PanelStageSelect : SelfRegister2UIComponentsMonobehaviour
{
	private Color red;

	private Color blue;

	[SerializeField]
	private UIScrollView chartScrollView;

	[SerializeField]
	private Transform normalModeObj;

	[SerializeField]
	private Transform hardModeObj;

	[SerializeField]
	private Transform classicModeObj;

	[SerializeField]
	private UIScrollView infoScrollView;

	[SerializeField]
	private Transform infoButtons;

	[SerializeField]
	private Transform modeButtons;

	[SerializeField]
	private StorybookGradeRewardsUI gradeRewardUI;

	[SerializeField]
	private StorybookGradeRewardsUI gradeRewardUIForClassic;

	[SerializeField]
	private Transform likeAbilityState;

	private Dictionary<int, ScenarioUI> id2ScenarioUI;

	private List<ScenarioUI> scenarioUIs;

	private List<GameObject> chartEmptyObjs;

	private bool isStageNeedUnitPopup;

	private const int gridCellHeight = 200;

	private const int scrollviewOriginPosY = -405;

	private const int scrollviewOriginOffsetY = -17;

	[SerializeField]
	private ScenarioChapterImageController chapterImageController;

	private ScenarioUI[] focusingScenario;

	public Action OnLevelModeChangedCallback;

	private UIGrid grid
	{
		get
		{
			return null;
		}
	}

	private ScenarioUI focusing
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void Start()
	{
	}

	private void PlayBGMByScenraioModelLevel()
	{
	}

	private void UpdateGradeRewardUI()
	{
	}

	public void UpdateScenarioUIs()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void DrawChart()
	{
	}

	private List<int> GetChartItemIds()
	{
		return null;
	}

	private void GetPlayable(EnumData.ScenarioModeLevel scenarioModeLevel, int scenarioId)
	{
	}

	private void SetModeUIState()
	{
	}

	private void AttachFlowLine(ScenarioUI target)
	{
	}

	private void UpdateFlowLine(ScenarioUI target)
	{
	}

	private void ClearGrid()
	{
	}

	private int GetLastRowNum(int storybookId)
	{
		return 0;
	}

	private void OnToggleUnitTree(bool active)
	{
	}

	private void UpdateFocusingScenarioInfo()
	{
	}

	public void UpdateBtns()
	{
	}

	public void OnNeedUnitBtnClick()
	{
	}

	private void ShowNeedUnitList()
	{
	}

	private string GetGuideToEnter()
	{
		return null;
	}

	private void UpdateLevelMode()
	{
	}

	public void ScrollCenterFocusingScenario()
	{
	}

	public void UpdateChartScrollBG()
	{
	}

	private void InitScenarioFocusing()
	{
	}

	private void OnItemClick(ScenarioUI scenarioUI)
	{
	}

	private void OnDoubleClicked(ScenarioUI scenarioUI)
	{
	}

	private void OnItemSelected(ScenarioUI scenarioUI)
	{
	}

	private void OnItemDeselected(ScenarioUI scenarioUI)
	{
	}

	public void OnLevelModeChanged()
	{
	}

	public void OnBtnClassicStamp()
	{
	}

	public void OnBtnResetClassicMode()
	{
	}

	public void OnBtnDownLoad()
	{
	}

	private void StartDownLoadEventScript()
	{
	}

	[DebuggerHidden]
	public IEnumerator DownLoadEventScript(DateTime openDate)
	{
		return null;
	}

	public void StopDownLoadEventScript()
	{
	}

	public void GoScenarioCheck()
	{
	}

	[DebuggerHidden]
	private IEnumerator _GoScenarioCheck()
	{
		return null;
	}

	private void GoScenarioMap()
	{
	}

	public int CheckUnitEnergyCostForClassicMode(int scenarioId)
	{
		return 0;
	}

	public void GoWorldMap()
	{
	}

	public void showLikeAbilityState()
	{
	}

	private void ShowNeedUnitEnergy(int unitId)
	{
	}

	private void RecoverUnitEnergy(List<ulong> unitSrls, int cost)
	{
	}

	private void OnRecoverUnitEnergy(JsonData response)
	{
	}

	public void FocusScenario(int scenarioId)
	{
	}
}
