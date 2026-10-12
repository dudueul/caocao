using System;
using System.Collections.Generic;
using UnityEngine;

internal class RandomGenerater
{
	private static System.Random m_random;

	private static List<int> m_randomList;

	private static bool m_useList;

	private const int MaxValue = 100000;

	private const double MaxValueD = 100000.0;

	private static System.Random random
	{
		get
		{
			return null;
		}
	}

	public static int randomIdx { get; private set; }

	public static int randomValue
	{
		get
		{
			return 0;
		}
	}

	public static void SetSeed(int seed)
	{
	}

	public static int Next()
	{
		return 0;
	}

	public static int Next(int max, bool isSimulate = false)
	{
		return 0;
	}

	public static int Next(int min, int max, bool isSimulate = false)
	{
		return 0;
	}

	public static int Next(Vector2 range, bool isSimulate = false)
	{
		return 0;
	}

	public static void NextBytes(byte[] buffer)
	{
	}

	public static double NextDouble(bool isSimulate = false)
	{
		return 0.0;
	}

	public static List<T> ShuffleList<T>(List<T> list, bool isSimulate = false)
	{
		return null;
	}

	public static T Pick<T>(params T[] objs)
	{
		return default(T);
	}

	public static T Pick<T>(List<T> objs)
	{
		return default(T);
	}

	public static T Pick<T>(List<T> objs, List<int> rates)
	{
		return default(T);
	}

	public static void SetRandomList(params int[] randoms)
	{
	}

	public static void SetRandomIndex(int idx)
	{
	}

	public static void ClearList()
	{
	}

	public static bool MatchRandom(int idx, int value)
	{
		return false;
	}

	private static int NextFromList()
	{
		return 0;
	}

	private static int NextFromList(int max)
	{
		return 0;
	}

	public static int NextFromList(int min, int max)
	{
		return 0;
	}

	public static void NextBytesFromList(byte[] buffer)
	{
	}

	public static double NextDoubleFromList()
	{
		return 0.0;
	}
}
