using System;
using System.Collections.Generic;

[Serializable]
public class __StoryMission
{
	public int id;

	public int storyID;

	public int storyType;

	public List<int> missionCount;

	public List<int> rewardType;

	public List<int> reward;

	public void CopyTo(StoryMission dest)
	{
	}

	public void Reset()
	{
	}
}
