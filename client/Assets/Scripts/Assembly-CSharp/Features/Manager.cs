using System;
using System.Collections.Generic;

namespace Features
{
	public static class Manager
	{
		private static Action<Type, int, string> OnUnlocked__BackingField;

		private static Dictionary<string, int> ms_nameToId;

		private static Dictionary<int, Feature> ms_idToFeature;

		private static Feature ms_tech;

		private static Feature ms_vip;

		private static bool ms_init;

		public static event Action<Type, int, string> OnUnlocked
		{
			add
			{
			}
			remove
			{
			}
		}

		static Manager()
		{
		}

		public static void OnTechResearch()
		{
		}

		public static void OnVipLevelChange()
		{
		}

		public static void OnSaveFlagChange()
		{
		}

		public static bool IsEnabled(string featureName)
		{
			return false;
		}

		public static bool IsEnabled(string featureName, ulong uid)
		{
			return false;
		}

		public static bool IsEnabled(int id)
		{
			return false;
		}

		public static bool IsEnabled(int id, ulong uid)
		{
			return false;
		}

		public static int GetValue(string featureName)
		{
			return 0;
		}

		public static int GetValue(string featureName, ulong uid)
		{
			return 0;
		}

		public static int GetValue(int id)
		{
			return 0;
		}

		public static int GetValue(int id, ulong uid)
		{
			return 0;
		}

		public static string GetLockedMessage(string featureName)
		{
			return null;
		}

		public static string GetLockedMessage(string featureName, ulong uid)
		{
			return null;
		}

		public static string GetLockedMessage(int id)
		{
			return null;
		}

		public static string GetLockedMessage(int id, ulong uid)
		{
			return null;
		}

		public static int GetLevelWithValue(string featureName, int value)
		{
			return 0;
		}

		public static int GetLevelWithValue(string featureName, ulong uid, int value)
		{
			return 0;
		}

		public static int GetLevelWithValue(int id, int value)
		{
			return 0;
		}

		public static int GetLevelWithValue(int id, ulong uid, int value)
		{
			return 0;
		}

		private static bool Init()
		{
			return false;
		}

		private static int NameToId(string name)
		{
			return 0;
		}

		private static Feature IdToFeature(int id)
		{
			return null;
		}
	}
}
