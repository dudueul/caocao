using UnityEngine;

public class BingoEventCompleteRewardListItem : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized title;

	[SerializeField]
	private UILabelLocalized value;

	[SerializeField]
	private UIButton btnReceive;

	[SerializeField]
	private UILabelLocalized btnLabel;

	[SerializeField]
	private UIGrid rewardGrid;

	[SerializeField]
	private GameObject clear;

	private CustomEventRewardLogs eventRewardLogs;

	public CustomEventReward eventReward { get; private set; }

	public void SetReward(CustomEventReward eventReward, CustomEventRewardLogs eventRewardLogs)
	{
	}

	public void UpdateRecordValue(int recordValue)
	{
	}

	private void UpdateReward()
	{
	}

	public void SetOnClickHandler(EventDelegate.Callback requestCallback)
	{
	}
}
