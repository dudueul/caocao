using System;
using UnityEngine;

namespace Battle
{
	public class Ignition
	{
		private static bool ms_ignitionTest;

		private static Action IgnitionSpreadTest__BackingField;

		public static event Action IgnitionSpreadTest
		{
			add
			{
			}
			remove
			{
			}
		}

		public static void TestIgnitionSpread()
		{
		}

		public static float GetIgnitionRate(IntVector2 pos)
		{
			return 0f;
		}

		public static float GetSpreadRate(IntVector2 pos)
		{
			return 0f;
		}

		public static float GetExtinguishRate(IntVector2 pos)
		{
			return 0f;
		}

		private static int GetTileIgnitionRate(IntVector2 pos)
		{
			return 0;
		}

		public static Coroutine Ignite(int id, IntVector2 pos, bool applyIgnitionDamage)
		{
			return null;
		}

		public static Coroutine ApplyIgnite(int id, IntVector2 pos, bool applyIgnitionDamage)
		{
			return null;
		}

		public static void Extinguish(int id, IntVector2 pos)
		{
		}

		public static void ApplyExtinguish(int id, IntVector2 pos)
		{
		}
	}
}
