using System;
using System.Collections.Generic;

[Serializable]
public class __HotTimeReward
{
	public int id;

	public int country;

	public string title;

	public string desc;

	public int eventRewardType;

	public int eventRewardVal;

	public int eventRewardID;

	public List<int> timeBoxId;

	public List<int> eventBoxId;

	public void CopyTo(HotTimeReward dest)
	{
	}

	public void Reset()
	{
	}
}
