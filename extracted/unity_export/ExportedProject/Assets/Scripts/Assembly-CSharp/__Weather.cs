using System;

[Serializable]
public class __Weather
{
	public string name;

	public int id;

	public string icon;

	public int serenity;

	public int cloud;

	public int rain;

	public int snow;

	public int storm;

	public int fog;

	public int none;

	public void CopyTo(Weather dest)
	{
	}

	public void Reset()
	{
	}
}
