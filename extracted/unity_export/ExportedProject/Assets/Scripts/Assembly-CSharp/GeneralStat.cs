using System;
using System.Runtime.InteropServices;
using CodeStage.AntiCheat.ObscuredTypes;

[Serializable]
[StructLayout((LayoutKind)0, Size = 60)]
public struct GeneralStat
{
	public ObscuredInt str;

	public ObscuredInt cha;

	public ObscuredInt wis;

	public ObscuredInt dex;

	public ObscuredInt luck;

	// C# has no syntax for parameterized property 'Item'.
	public int get_Item(EnumData.UnitStatUpType statType)
	{
		return 0;
	}

	public void set_Item(EnumData.UnitStatUpType statType, int value)
	{
	}

	public int sum
	{
		get
		{
			return 0;
		}
	}

	public GeneralStat(int str, int cha, int wis, int dex, int luck)
	{
		this.str = default(ObscuredInt);
		this.cha = default(ObscuredInt);
		this.wis = default(ObscuredInt);
		this.dex = default(ObscuredInt);
		this.luck = default(ObscuredInt);
	}

	public static implicit operator GeneralStat(int d)
	{
		return default(GeneralStat);
	}

	public static GeneralStat operator -(GeneralStat a)
	{
		return default(GeneralStat);
	}

	public static GeneralStat operator +(GeneralStat a, GeneralStat b)
	{
		return default(GeneralStat);
	}

	public static GeneralStat operator +(GeneralStat a, int d)
	{
		return default(GeneralStat);
	}

	public static GeneralStat operator +(int d, GeneralStat a)
	{
		return default(GeneralStat);
	}

	public static GeneralStat operator -(GeneralStat a, GeneralStat b)
	{
		return default(GeneralStat);
	}

	public static GeneralStat operator -(GeneralStat a, int d)
	{
		return default(GeneralStat);
	}

	public static GeneralStat operator -(int d, GeneralStat a)
	{
		return default(GeneralStat);
	}

	public static GeneralStat operator *(GeneralStat a, int d)
	{
		return default(GeneralStat);
	}

	public static GeneralStat operator *(int d, GeneralStat a)
	{
		return default(GeneralStat);
	}

	public static GeneralStat operator *(float d, GeneralStat a)
	{
		return default(GeneralStat);
	}

	public static GeneralStat operator *(GeneralStat a, float d)
	{
		return default(GeneralStat);
	}
}
