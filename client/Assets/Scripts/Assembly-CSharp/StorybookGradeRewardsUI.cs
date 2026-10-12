using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using UnityEngine;

public class StorybookGradeRewardsUI : MonoBehaviour
{
	private bool m_init;

	private Dictionary<int, RewardInfo> normalRewardList;

	private Dictionary<int, RewardInfo> hardRewardList;

	private Dictionary<int, RewardInfo> classicRewardList;

	private ObscuredInt storybookId;

	private EnumData.ScenarioModeLevel scenarioModeLevel;

	private int nextGradeCount;

	private RewardInfo nextReward;

	[SerializeField]
	private UILabelLocalized barText;

	[SerializeField]
	private UISprite barSprite;

	[SerializeField]
	private UIButton rewardListBtn;

	[SerializeField]
	private UIButton receiveBtn;

	[SerializeField]
	private UIButton classicStampBoxBtn;

	[SerializeField]
	private UIButton classicResetBtn;

	[SerializeField]
	private UILabelLocalized nextValueLabel;

	[SerializeField]
	private Transform rewardUI;

	[SerializeField]
	private PrefabInstance itemUIPrefabInstance;

	[SerializeField]
	private Transform dim;

	[SerializeField]
	private Transform classicStampReward;

	[SerializeField]
	private UILabelLocalized classicStampInfomationLabel;

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}

	public void UpdateUI()
	{
	}

	private void UpdateClassicStampBoxInfomationUI()
	{
	}

	private void UpdateClassicStampUI()
	{
	}

	private void UpdateClassicStampResetUI()
	{
	}

	private void Init()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void RewardListBtnClicked()
	{
	}

	public void OnClassicStampBoxInfomationClicked()
	{
	}

	private void RequestReward(int _storybookId, int _gradeCount, EnumData.ScenarioModeLevel _scenarioModeLevel)
	{
	}

	private void RequestRewardResponse(JsonData response)
	{
	}

	public void OnScenarioModeLevelChanged(EnumData.ScenarioModeLevel modeLevel)
	{
	}
}
