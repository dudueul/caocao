using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;
using LitJson;
using UnityEngine;

public class FinishScenario : FinishBase
{
	[SerializeField]
	private UILabelLocalized uiLabel_UserExp;

	[SerializeField]
	private UILabelLocalized uiLabel_Silver;

	[SerializeField]
	private UILabelLocalized uiLabel_Food;

	[SerializeField]
	private UILabelLocalized uiLabel_Stamp;

	private UIButton m_btnReplay;

	private UIButton m_btnFlowChart;

	private UIButton m_btnPlay;

	private UIButton m_btnGoWorldMap;

	[SerializeField]
	private Transform m_itemGridUI;

	private List<GameObject> sealObjs;

	private static StringBuilder ms_sb;

	private int m_nextScenarioId
	{
		get
		{
			return 0;
		}
	}

	private void Start()
	{
	}

	public void OnInit(Transform parent)
	{
	}

	protected override void OnDestroy()
	{
	}

	public override void ShowFinish()
	{
	}

	protected override void RestartBattle()
	{
	}

	public void ShowVictoryGrade(int grade)
	{
	}

	public void UpdateChildUIStatus()
	{
	}

	public void UpdatePanelUI()
	{
	}

	[DebuggerHidden]
	private IEnumerator SendSetRecentScenario(string goSceneName, bool autoDestroy = true)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator SendScenarioBattleEnd(int scenarioId, int victoryGrade)
	{
		return null;
	}

	private void OnProcessBattleEnd(JsonData response)
	{
	}

	[DebuggerHidden]
	private IEnumerator OnPostEffect(int addUserExp)
	{
		return null;
	}

	[DebuggerHidden]
	protected override IEnumerator ShowPedlarInstanceEffect()
	{
		return null;
	}

	private void UpdateScenaioPlayBtn()
	{
	}

	private int GetAvailableNextScenarioId(Scenario scenario)
	{
		return 0;
	}

	private void SetButtonsEnable(bool isEnable)
	{
	}

	private void ShowStampingEffect()
	{
	}

	public void UpdateGoods(int addSilver, int addFood, int addStamp, int addUserExp)
	{
	}
}
