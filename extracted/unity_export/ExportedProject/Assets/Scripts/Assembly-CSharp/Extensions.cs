using System.Collections.Generic;
using UnityEngine;

public static class Extensions
{
	private static readonly float[,] ms_jobTypeBonusArray;

	public static float DecFixedTime(this float val)
	{
		return 0f;
	}

	public static float Dec(this float val, float _DecVal)
	{
		return 0f;
	}

	public static bool Intersect(this Rect rt1, Rect rt2)
	{
		return false;
	}

	public static bool Intersect(this Rect rt, Vector2 point)
	{
		return false;
	}

	public static T GetSafe<T, K>(this Dictionary<K, T> _Container, K _Key) where T : class
	{
		return null;
	}

	public static void DeleteImmediateChild(this Transform _Parent)
	{
	}

	public static void DeleteChild(this Transform _Parent)
	{
	}

	public static void DisposeChild(this Transform _Parent)
	{
	}

	public static T ToEnum<T>(this string enumText)
	{
		return default(T);
	}

	public static T ToEnum<T>(this string enumText, T defaultValue) where T : struct
	{
		return default(T);
	}

	public static List<T> Copy<T>(this List<T> _FromList)
	{
		return null;
	}

	public static T PublicDeepCopy<T>(this T _Original)
	{
		return default(T);
	}

	public static float GetScoreForPick(this UserUnit unit, EnumData.BattleOwnTeamDeckType optimal)
	{
		return 0f;
	}

	public static float GetJobTypeBonus(this UserUnit unit, EnumData.BattleOwnTeamDeckType optimal)
	{
		return 0f;
	}

	public static float GetEfficiencyForPick(this UserUnit unit, EnumData.BattleOwnTeamDeckType optimal)
	{
		return 0f;
	}

	public static UnitData GetBaseUnitData(this GameObject go)
	{
		return null;
	}

	public static UnitData GetBaseUnitData(this UnitData unitData)
	{
		return null;
	}

	public static UnitData GetBaseUnitData(this UnitController unitController)
	{
		return null;
	}

	public static T GetBaseUnitComponent<T>(this UnitData unitData) where T : Component
	{
		return null;
	}

	public static T GetBaseUnitComponent<T>(this UnitController unitController) where T : Component
	{
		return null;
	}

	public static T GetBaseUnitComponent<T>(this GameObject go) where T : Component
	{
		return null;
	}

	public static GameObject GetBaseUnitObject(this GameObject go)
	{
		return null;
	}

	public static GameObject GetBaseUnitObject(this Component comp)
	{
		return null;
	}

	public static void ChangeValue(this object obj, string fieldName, int value)
	{
	}
}
