using LitJson;
using UnityEngine;

public class UserStatusMissionItem : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized m_title;

	[SerializeField]
	private UILabelLocalized m_rewardName;

	[SerializeField]
	private UILabelLocalized m_checkValue;

	[SerializeField]
	private UISprite m_rewardGoodsIcon;

	[SerializeField]
	private GameObject m_receiveObj;

	[SerializeField]
	private GameObject m_rewardObj;

	private Achievement m_achievement;

	private bool m_isRewardTicketItem;

	private bool m_waitRequest;

	public void Init(Achievement achievement)
	{
	}

	private void UpdateRewardUI(RewardInfo rewardInfo)
	{
	}

	private void UpdateRewardBtn()
	{
	}

	private void UpdateCheckValue()
	{
	}

	public void OnReceiveBtnClick()
	{
	}

	private void OnCompleteCallback(JsonData response)
	{
	}

	private bool GetSubMissionAllClear()
	{
		return false;
	}
}
