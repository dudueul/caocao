using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class MLayoutToolbar
{
	[Serializable]
	private class ActionButton
	{
		public GUIStyle style;

		public bool isSelected;

		public string label;

		public float width;

		public float height;
	}

	[SerializeField]
	private List<ActionButton> _buttonList;

	[SerializeField]
	private int _selectedIndex;

	public int selectedIndex
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public void addItem(string label, GUIStyle style, float width = 150f, float height = 20f)
	{
	}

	public void OnGUI()
	{
	}
}
