using System;
using System.Collections.Generic;

[Serializable]
public class __CompetitionMap
{
	public string name;

	public int id;

	public string displayMode;

	public int battleMapId;

	public string battleMapImage;

	public int battleMode;

	public List<int> tileId;

	public string description;

	public List<int> jobtype;

	public int axisX;

	public int axisY;

	public void CopyTo(CompetitionMap dest)
	{
	}

	public void Reset()
	{
	}
}
