using System;
using System.Collections.Generic;
using UnityEngine;

public class KeyBindData : IKeyBindData, IEquatable<KeyBindData>
{
	protected List<KeyCode> keyCodes;

	public KeyBindData(params KeyCode[] codes)
	{
	}

	public bool Equals(KeyBindData obj)
	{
		return false;
	}

	public List<KeyCode> GetKeyCodes()
	{
		return null;
	}

	public KeyBindData Bind(params KeyCode[] codes)
	{
		return null;
	}

	public KeyCode GetKeyCode(int index)
	{
		return KeyCode.None;
	}

	public bool Contain(KeyCode code)
	{
		return false;
	}

	public int FindIndex(KeyCode code)
	{
		return 0;
	}

	public override string ToString()
	{
		return null;
	}
}
