using System.Collections.Generic;
using UnityEngine;

public static class KeyBindingHelper
{
	private static readonly HashSet<KeyCode> _customizableKey;

	private static readonly HashSet<KeyCode> _customizableModifier;

	public static bool isCustomizable(this KeyCode code)
	{
		return false;
	}

	public static KeyBindData ParseFromJsonString(string text)
	{
		return null;
	}

	public static string ToJsonString(this KeyBindData data)
	{
		return null;
	}
}
