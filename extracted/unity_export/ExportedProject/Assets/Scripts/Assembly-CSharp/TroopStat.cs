using System;
using System.Runtime.InteropServices;
using CodeStage.AntiCheat.ObscuredTypes;

[Serializable]
[StructLayout((LayoutKind)0, Size = 72)]
public struct TroopStat
{
	public ObscuredInt atk;

	public ObscuredInt def;

	public ObscuredInt inte;

	public ObscuredInt agi;

	public ObscuredInt mor;

	public ObscuredInt move;

	public static readonly TroopStat zero;

	// C# has no syntax for parameterized property 'Item'.
	public int get_Item(EnumData.StatType statType)
	{
		return 0;
	}

	// C# has no syntax for parameterized property 'Item'.
	public int get_Item(int idx)
	{
		return 0;
	}

	public TroopStat(int atk, int def, int inte, int agi, int mor, int move)
	{
		this.atk = default(ObscuredInt);
		this.def = default(ObscuredInt);
		this.inte = default(ObscuredInt);
		this.agi = default(ObscuredInt);
		this.mor = default(ObscuredInt);
		this.move = default(ObscuredInt);
	}

	public int CalcCombatPower(EnumData.unitType jobStyle)
	{
		return 0;
	}

	public EnumData.StatType GetPrimaryStatType(out int maxValue)
	{
		maxValue = default(int);
		return EnumData.StatType.ATK;
	}

	public int GetPrimaryStat()
	{
		return 0;
	}

	public static implicit operator TroopStat(int d)
	{
		return default(TroopStat);
	}

	public static TroopStat operator +(TroopStat a, TroopStat b)
	{
		return default(TroopStat);
	}

	public static TroopStat operator +(TroopStat a, int d)
	{
		return default(TroopStat);
	}

	public static TroopStat operator -(TroopStat a, TroopStat b)
	{
		return default(TroopStat);
	}

	public static TroopStat operator -(TroopStat a, int d)
	{
		return default(TroopStat);
	}

	public static TroopStat operator +(int d, TroopStat a)
	{
		return default(TroopStat);
	}

	public static TroopStat operator -(int d, TroopStat a)
	{
		return default(TroopStat);
	}

	public static TroopStat operator *(TroopStat a, int d)
	{
		return default(TroopStat);
	}

	public static TroopStat operator *(int d, TroopStat a)
	{
		return default(TroopStat);
	}

	public static TroopStat operator *(float d, TroopStat a)
	{
		return default(TroopStat);
	}

	public static TroopStat operator *(TroopStat a, float d)
	{
		return default(TroopStat);
	}

	public static bool operator ==(int d, TroopStat a)
	{
		return false;
	}

	public static bool operator ==(TroopStat a, int d)
	{
		return false;
	}

	public static bool operator ==(TroopStat a, TroopStat b)
	{
		return false;
	}

	public static bool operator !=(int d, TroopStat a)
	{
		return false;
	}

	public static bool operator !=(TroopStat a, int d)
	{
		return false;
	}

	public static bool operator !=(TroopStat a, TroopStat b)
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

	public static TroopStat Min(TroopStat a, TroopStat b)
	{
		return default(TroopStat);
	}

	public static TroopStat Max(TroopStat a, TroopStat b)
	{
		return default(TroopStat);
	}
}
