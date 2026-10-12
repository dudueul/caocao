using System;
using LitJson;

[Serializable]
public class FairRule
{
	public int tier { get; set; }

	public int treasureEnhance { get; set; }

	public int treasureEnhancePassive { get; set; }

	public bool rune { get; set; }

	public bool runeSet { get; set; }

	public int runeEnhance { get; set; }

	public string desc { get; set; }

	public FairRule()
	{
	}

	public FairRule(GameModeRule gameModeRule, int tierGrade)
	{
	}

	public FairRule(JsonData jsonData)
	{
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	public override string ToString()
	{
		return null;
	}
}
