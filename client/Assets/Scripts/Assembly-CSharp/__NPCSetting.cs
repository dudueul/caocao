using System;
using System.Collections.Generic;

[Serializable]
public class __NPCSetting
{
	public int id;

	public List<int> setUnitLv;

	public List<int> setUnitLvNum;

	public int treasureEnhanceLv;

	public int unitPassiveNum;

	public int battleFormationLv;

	public int trainNum;

	public void CopyTo(NPCSetting dest)
	{
	}

	public void Reset()
	{
	}
}
