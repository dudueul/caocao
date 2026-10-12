using System;

public class BattleMapInfo : IEquatable<BattleMapInfo>
{
	public EnumData.BattleMode battleMode;

	public int id;

	public int battleMapID;

	public bool canPlay;

	public EnumData.ScenarioModeLevel scenarioMode;

	public bool isMyTown;

	public int minLevel;

	public bool hardMode
	{
		get
		{
			return false;
		}
	}

	public bool classicMode
	{
		get
		{
			return false;
		}
	}

	public override string ToString()
	{
		return null;
	}

	public override int GetHashCode()
	{
		return 0;
	}

	public override bool Equals(object obj)
	{
		return false;
	}

	public bool Equals(BattleMapInfo obj)
	{
		return false;
	}
}
