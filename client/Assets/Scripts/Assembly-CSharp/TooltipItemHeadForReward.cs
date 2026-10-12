using UnityEngine;

public class TooltipItemHeadForReward : TooltipComponent
{
	private UserItem m_userItem;

	public override void UpdateComponent(Type type, int id)
	{
	}

	public override void UpdateComponent(Type type, int id, ITransmittable data)
	{
	}

	public override void UpdateComponent(Type type, int id, Type triggerType, int triggerId)
	{
	}

	public override void UpdateComponent(Type type, int id, object[] parameters)
	{
	}

	protected void UpdateComponentImpl(Type type, int id, RewardInfo rewardInfo)
	{
	}

	protected void UpdateComponentImpl(Type type, int id, UserItem userItem, RewardInfo rewardInfo)
	{
	}

	private void UpdateName(Type type, UserItem item)
	{
	}

	private void UpdateName(Type type, int id, RewardInfo rewardInfo)
	{
	}

	private void UpdateType(Type type, int id, RewardInfo rewardInfo)
	{
	}

	protected virtual void UpdateNum(Type type, int id, RewardInfo rewardInfo)
	{
	}

	private void UpdateLVLimit(Type type, int id, RewardInfo rewardInfo)
	{
	}

	private void UpdateIconFrame(Type type, int id, RewardInfo rewardInfo)
	{
	}

	private void UpdateIconFrame(Type type, int id, UserItem userItem)
	{
	}

	private void UpdateBoard(Type type, int id)
	{
	}

	private void AddClickHandler(Type type, int id)
	{
	}

	private void ClearClickHandler()
	{
	}

	private void UpdateTreasurePassive(Type type, UserItem item)
	{
	}

	private void UpdatePassiveIcon(Transform root, int index)
	{
	}

	private void PassiveIconClicked()
	{
	}
}
