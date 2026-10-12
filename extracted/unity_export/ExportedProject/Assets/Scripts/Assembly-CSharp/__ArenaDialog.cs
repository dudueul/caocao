using System;
using System.Collections.Generic;

[Serializable]
public class __ArenaDialog
{
	public int id;

	public int scenario;

	public int theme;

	public int level;

	public int needAllUnit;

	public int speechType;

	public List<int> speechUnit;

	public List<int> speechWindow;

	public List<string> speech;

	public void CopyTo(ArenaDialog dest)
	{
	}

	public void Reset()
	{
	}
}
