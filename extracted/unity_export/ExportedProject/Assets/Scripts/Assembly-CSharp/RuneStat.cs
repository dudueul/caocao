using System;
using System.Runtime.InteropServices;
using CodeStage.AntiCheat.ObscuredTypes;

[Serializable]
[StructLayout((LayoutKind)0, Size = 84)]
public struct RuneStat
{
	public ObscuredInt atk;

	public ObscuredInt def;

	public ObscuredInt inte;

	public ObscuredInt agi;

	public ObscuredInt mor;

	public ObscuredInt hp;

	public ObscuredInt mp;

	public static readonly RuneStat zero;

	// C# has no syntax for parameterized property 'Item'.
	public int get_Item(EnumData.RuneMainStat statType)
	{
		return 0;
	}

	// C# has no syntax for parameterized property 'Item'.
	public int get_Item(int idx)
	{
		return 0;
	}

	public RuneStat(int atk, int def, int inte, int agi, int mor, int hp, int mp)
	{
		this.atk = default(ObscuredInt);
		this.def = default(ObscuredInt);
		this.inte = default(ObscuredInt);
		this.agi = default(ObscuredInt);
		this.mor = default(ObscuredInt);
		this.hp = default(ObscuredInt);
		this.mp = default(ObscuredInt);
	}

	public static implicit operator RuneStat(int d)
	{
		return default(RuneStat);
	}

	public static RuneStat operator +(RuneStat a, RuneStat b)
	{
		return default(RuneStat);
	}

	public static RuneStat operator +(RuneStat a, int d)
	{
		return default(RuneStat);
	}

	public static RuneStat operator -(RuneStat a, RuneStat b)
	{
		return default(RuneStat);
	}

	public static RuneStat operator -(RuneStat a, int d)
	{
		return default(RuneStat);
	}

	public static RuneStat operator +(int d, RuneStat a)
	{
		return default(RuneStat);
	}

	public static RuneStat operator -(int d, RuneStat a)
	{
		return default(RuneStat);
	}

	public static RuneStat operator *(RuneStat a, int d)
	{
		return default(RuneStat);
	}

	public static RuneStat operator *(int d, RuneStat a)
	{
		return default(RuneStat);
	}

	public static RuneStat operator *(float d, RuneStat a)
	{
		return default(RuneStat);
	}

	public static RuneStat operator *(RuneStat a, float d)
	{
		return default(RuneStat);
	}

	public static bool operator ==(int d, RuneStat a)
	{
		return false;
	}

	public static bool operator ==(RuneStat a, int d)
	{
		return false;
	}

	public static bool operator ==(RuneStat a, RuneStat b)
	{
		return false;
	}

	public static bool operator !=(int d, RuneStat a)
	{
		return false;
	}

	public static bool operator !=(RuneStat a, int d)
	{
		return false;
	}

	public static bool operator !=(RuneStat a, RuneStat b)
	{
		return false;
	}

	public override bool Equals(object obj)
	{
		return false;
	}

	public override int GetHashCode()
	{
		return 0;
	}

	public static RuneStat Min(RuneStat a, RuneStat b)
	{
		return default(RuneStat);
	}

	public static RuneStat Max(RuneStat a, RuneStat b)
	{
		return default(RuneStat);
	}
}
