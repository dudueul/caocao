using System.Collections.Generic;
using UnityEngine;

public static class KeyCodeUtil
{
	private static readonly Dictionary<KeyCode, string> _specificNames;

	private static readonly Dictionary<KeyCode, string> _code2string;

	private static readonly Dictionary<string, KeyCode> _string2code;

	private static readonly HashSet<KeyCode> _ignoreCodes;

	static KeyCodeUtil()
	{
	}

	public static KeyCode ToCode(string name)
	{
		return KeyCode.None;
	}

	public static string ToName(this KeyCode code)
	{
		return null;
	}

	private static void assign(KeyCode code, string name)
	{
	}
}
