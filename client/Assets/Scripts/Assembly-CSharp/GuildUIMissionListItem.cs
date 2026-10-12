using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class GuildUIMissionListItem : MonoBehaviour
{
	public UILabelLocalized labelTitle;

	public UILabelLocalized labelDesc;

	public UILabelLocalized labelExp;

	public PrefabInstance personItemUIPrefab;

	public PrefabInstance personGoodUIPrefab;

	public PrefabInstance guildItemUIPrefab;

	public PrefabInstance guildGoodUIPrefab;

	public UILabelLocalized[] labelContributions;

	public UILabelLocalized[] labelContributionValues;

	public UISprite spriteGauge;

	public UILabelLocalized labelProceed;

	public GameObject objDimmed;

	private GuildMission mission;

	private GuildPopupMission.MissionType missionType;

	private ItemUI personItemUI;

	private GoodUI personGoodUI;

	private ItemUI guildItemUI;

	private GoodUI guildGoodUI;

	private void Awake()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public void OnContributionClicked()
	{
	}

	public void UpdateUI(GuildWeeklyMission missionData, GuildMission guildMission, GuildPopupMission.MissionType missionType)
	{
	}

	private void SetRewardUI(ItemUI itemUI, GoodUI goodUI, UILabelLocalized labelExp, Reward reward, bool isUserReward)
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateContributionMembers(GuildWeeklyMission missionData, GuildMission guildMission)
	{
		return null;
	}

	private void OnDestroy()
	{
	}
}
