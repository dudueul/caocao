using System;
using UnityEngine;

public class KeyboardBindAttribute : Attribute, IKeyBindAttribute
{
	public KeyBindData defaultSetting { get; private set; }

	public string description { get; private set; }

	public bool editable { get; private set; }

	public KeyboardBindAttribute(KeyCode key, string title, bool editable = true)
	{
	}

	public KeyboardBindAttribute(KeyBindData bindKey, string title, bool editable = true)
	{
	}
}
