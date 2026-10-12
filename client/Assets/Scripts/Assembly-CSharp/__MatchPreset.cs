using System;
using System.Collections.Generic;

[Serializable]
public class __MatchPreset
{
	public int id;

	public int town;

	public int saveflagId;

	public int value;

	public int castleLv;

	public string nationName;

	public int lordUnitId;

	public int mayorUnitId;

	public List<int> unitId;

	public List<int> unitLv;

	public List<int> slot;

	public List<int> soldierId;

	public List<int> soldierLv;

	public void CopyTo(MatchPreset dest)
	{
	}

	public void Reset()
	{
	}
}
