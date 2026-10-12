using System;
using UnityEngine;

public class CaocaoButton : UIButton
{
	public Color selectedColorTop;

	public Color selectedColorBottom;

	public Color unselectedColor;

	public Action<CaocaoButton> OnButtonClicked;

	private static string spriteNameFormat;

	private static string postfix_regex;

	private static string _n;

	private static string _s;

	private static string _p;

	private static string _o;

	private void Awake()
	{
	}

	public void UpdateButtonUI(bool selected)
	{
	}

	private void UpdateButtonSprites(bool selected)
	{
	}

	private void UpdateTextColor(bool selected)
	{
	}
}
