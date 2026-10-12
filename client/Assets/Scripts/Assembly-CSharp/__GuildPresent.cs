using System;
using System.Collections.Generic;

[Serializable]
public class __GuildPresent
{
	public int id;

	public int item;

	public List<int> contribution;

	public List<int> maxLimit;

	public void CopyTo(GuildPresent dest)
	{
	}

	public void Reset()
	{
	}
}
