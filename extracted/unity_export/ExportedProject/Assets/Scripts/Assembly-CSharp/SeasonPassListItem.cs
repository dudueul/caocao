using System;
using LitJson;
using UnityEngine;

public class SeasonPassListItem : MonoBehaviour
{
	private SeasonPassReward normalReward;

	private SeasonPassReward eliteReward;

	private Action<JsonData> completeCallback;

	private string towerTextureName;

	private GameObject m_objDim;

	private UILabelLocalized m_labelLevel;

	private Transform m_objNormal;

	private Transform m_objElite;

	public bool completeAbled { get; private set; }

	private GameObject objDim
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelLevel
	{
		get
		{
			return null;
		}
	}

	private Transform objNormal
	{
		get
		{
			return null;
		}
	}

	private Transform objElite
	{
		get
		{
			return null;
		}
	}

	public void Initiate(SeasonPassReward normalReward, SeasonPassReward eliteReward, Action<JsonData> completeCallback)
	{
	}

	private void InitLevelUI(SeasonPassReward rewardData, Transform obj)
	{
	}

	public void UpdateUI(int level, int normalLevel, int eliteLevel, bool prevCompleted, bool purchasedTicket)
	{
	}

	private void UpdateLevelUI(bool disabled, int level, int completeLevel, SeasonPassReward reward, Transform obj, bool purchased)
	{
	}

	public void OnCompleteNormalButton()
	{
	}

	public void OnCompleteEliteButton()
	{
	}

	private void OnGetRewardResponse(JsonData response)
	{
	}

	private void OnDestroy()
	{
	}
}
