using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class SeasonPassButton : MonoBehaviour
{
	private const string KEY_CHECKED_LV = "SEASONPASS_CHECKED_LV";

	private const string KEY_CHECKED_ELITE_LV = "SEASONPASS_CHECKED_ELITE_LV";

	private SeasonPass pdSeasonPass;

	private int checkedLevel;

	private int checkedEliteLevel;

	private bool openPopup;

	private List<SeasonPassReward> lstNormalRewards;

	private List<SeasonPassReward> lstEliteRewards;

	private void Start()
	{
	}

	public void Reset(bool requestSeasonPassInfo, bool onlyElite = false)
	{
	}

	private void UpdateUI()
	{
	}

	private bool CheckOpened()
	{
		return false;
	}

	private void RequestSeasonPassInfo()
	{
	}

	private void OnResponseSeasonPassInfo(JsonData response)
	{
	}

	private void UpdateNewTag()
	{
	}

	private void CollectSeasonPassRewards()
	{
	}

	private void OnClick()
	{
	}

	private void OpenPopup()
	{
	}

	private void OnDestroy()
	{
	}
}
