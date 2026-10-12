using System;

[Serializable]
public class __SeasonPass
{
	public int id;

	public int country;

	public string title;

	public string desc;

	public string warningDesc;

	public string startDate;

	public string endDate;

	public int maxLevel;

	public int maxExp;

	public int getTrainPoint;

	public int getPaidPoint;

	public string textMessage;

	public void CopyTo(SeasonPass dest)
	{
	}

	public void Reset()
	{
	}
}
