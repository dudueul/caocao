using System;
using System.Collections.Generic;

public static class KeyBindingSettingDatas
{
	private static InputMode m_mode;

	private static Dictionary<KeyBindId, KeyBindData> m_settingData;

	private static Dictionary<KeyBindId, IKeyBindAttribute> m_metaData;

	static KeyBindingSettingDatas()
	{
	}

	private static string GetModeKey(InputMode mode, KeyBindId bindId)
	{
		return null;
	}

	private static Type GetAttributeType(InputMode mode)
	{
		return null;
	}

	public static void SetMode(InputMode mode = InputMode.KEYBOARD)
	{
	}

	private static void LoadUserSetting(InputMode mode)
	{
	}

	public static bool CheckDuplicateKeySetting(KeyBindData key, out KeyBindId bindId)
	{
		bindId = default(KeyBindId);
		return false;
	}

	public static KeySettingError SetBindKey(KeyBindId bindId, KeyBindData key, bool swap = false)
	{
		return KeySettingError.SUCCESS;
	}

	public static void ResetBindKeys()
	{
	}

	public static KeyBindData GetBindKey(KeyBindId bindId)
	{
		return null;
	}
}
