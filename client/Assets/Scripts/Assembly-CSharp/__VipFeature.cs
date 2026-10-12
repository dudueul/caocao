using System;
using System.Collections.Generic;

[Serializable]
public class __VipFeature
{
	public string name;

	public int id;

	public string desc;

	public string alert;

	public int descCountry;

	public List<int> vipLV;

	public List<int> val;

	public void CopyTo(VipFeature dest)
	{
	}

	public void Reset()
	{
	}
}
