using System;
using System.Collections.Generic;
using UnityEngine;

public class PopupSubMenuButtons : CaocaoUIModalPopup
{
	private static PopupSubMenuButtons self;

	[SerializeField]
	private UIButton button;

	[SerializeField]
	private UISprite bg;

	[SerializeField]
	private UILabelLocalized title;

	[SerializeField]
	private UIGrid grid;

	private List<Action> callback;

	private string normalSprite;

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	public static void Show(string localizedTitle, DepthManager.Grade depth, params object[] args)
	{
	}

	public static void Show(string localizedTitle, params object[] args)
	{
	}

	public static void Close()
	{
	}

	private static PopupSubMenuButtons InitPopup(string localizedTitle, params object[] args)
	{
		return null;
	}

	private void Init(string localizedTitle, object[] args)
	{
	}

	private void SetButton(UIButton btn, string text, Action callback)
	{
	}

	protected override void OnDestroy()
	{
	}

	public static void ShowUserMenu(ulong uid, string nationName)
	{
	}
}
