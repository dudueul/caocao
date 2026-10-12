using UnityEngine;

public class TooltipItemHead : TooltipComponent
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

	private void UpdateComponentImpl(Type type, int id, UserItem userItem, UserUnit userUnit, RewardInfo rewardInfo)
	{
	}

	private void UpdateName(Type type, UserItem item)
	{
	}

	private void UpdateName(Type type, int id)
	{
	}

	private void UpdateType(Type type, int id)
	{
	}

	private void UpdateNum(Type type, int id, UserItem userItem)
	{
	}

	private void UpdateLVLimit(Type type, int id, UserUnit userUnit)
	{
	}

	private void UpdateIconFrame(Type type, int id, UserItem userItem, UserUnit userUnit)
	{
	}

	private void UpdateBoard(Type type, int id)
	{
	}

	private void UpdateLock(Type type, int id, UserItem userItem, ulong userID)
	{
	}

	private void AddClickHandler(Type type, int id, UserItem userItem, ulong userID)
	{
	}

	private void ClearClickHandler()
	{
	}

	private void UpdateTreasurePassive(Type type, UserItem item)
	{
	}

	private void UpdateArenaTreasure(UserItem item)
	{
	}

	private void UpdatePassiveIcon(Transform root, int index)
	{
	}

	private void PassiveIconClicked()
	{
	}

	private void ToggleTreasureLock(UserItem userItem, ulong userID)
	{
	}

	private void SendTreasureLockRequest(UserItem userItem, bool _lock, ulong userID)
	{
	}
}
