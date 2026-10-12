using System;
using System.Collections.Generic;
using System.Globalization;
using UnityEngine;

public static class Localization
{
	public delegate byte[] LoadFunction(string path);

	private static bool m_loaded;

	public static LoadFunction loadFunction;

	private static Dictionary<string, string[]> mLangDictionary;

	private static Dictionary<string, string> mDictionary;

	private static Dictionary<string, string> mEventDictionary;

	private static Dictionary<string, string> mtutorialEventDictionary;

	private static Dictionary<string, string> mExtraLocalizationDictionary;

	private static Dictionary<string, string> mCheatDictionary;

	private static string[] mKnownLanguages;

	private static Dictionary<int, int> m_mergePair;

	public static CultureInfo currentCulture;

	public static StringComparer stringComparer;

	private static int mCurrentLangIdx;

	private static int m_mergeLangIndex;

	private static EnumData.Locale m_currentLang;

	private static string mCurrentEventScript;

	private static string mCurrentTutorialEventScript;

	public static string[] knownLanguages
	{
		get
		{
			return null;
		}
	}

	private static EnumData.Locale mCurrentLang
	{
		get
		{
			return EnumData.Locale.NONE;
		}
		set
		{
		}
	}

	public static EnumData.Locale language
	{
		get
		{
			return EnumData.Locale.NONE;
		}
		set
		{
		}
	}

	public static string eventScriptName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static string tutorialScriptName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static void Load()
	{
	}

	public static void Reload()
	{
	}

	public static void Clear()
	{
	}

	public static void LoadEventCSVData(string scriptName)
	{
	}

	private static bool LoadDictionary(string fileName, Dictionary<string, string> dictionary)
	{
		return false;
	}

	private static void LoadLocalization(string path, Dictionary<string, string> dictionary)
	{
	}

	private static bool LoadCSV(byte[] bytes, TextAsset asset, Dictionary<string, string> dictionary)
	{
		return false;
	}

	private static void AddCSV(BetterList<string> values, Dictionary<string, string[]> dictionary)
	{
	}

	private static void AddCSV(BetterList<string> values, Dictionary<string, string> dictionary)
	{
	}

	private static bool SelectLanguage(EnumData.Locale language)
	{
		return false;
	}

	public static string Get(string key)
	{
		return null;
	}

	public static string GetCheat(string key)
	{
		return null;
	}

	public static void ClearEventDictionary()
	{
	}

	public static void LoadExtraLocalizationCSVData()
	{
	}

	public static void LoadCheatLocalizationCSVData(byte[] csvBytes)
	{
	}
}
