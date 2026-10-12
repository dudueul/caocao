using System;
using UnityEngine;

public class GuildPopupSiegeWarAttackDeck : CaocaoUIModalPopup
{
	public UILabelLocalized labelDesc;

	public UIGrid gridFace;

	public GameObject btn1;

	public GameObject btn2;

	private string enemyGuildName;

	private Action callback;

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public static void Show(string enemyGuildName, Action callback)
	{
	}

	private void UpdateUI()
	{
	}

	public void SkipButton()
	{
	}

	public void EditButton()
	{
	}
}
