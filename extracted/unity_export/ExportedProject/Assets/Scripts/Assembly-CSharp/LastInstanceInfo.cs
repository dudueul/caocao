using System;

public class LastInstanceInfo
{
	public EnumData.EventType Mode;

	public int CategoryId;

	public DayOfWeek Day;

	public int StateId;

	public int InstanceId;

	public void SetNormalMode(int stateId, int instanceId)
	{
	}

	public void SetSpecialMode(int categoryId, DayOfWeek day, int instanceId)
	{
	}

	public void SetLimitedMode(int instanceId)
	{
	}

	public void Clear()
	{
	}
}
