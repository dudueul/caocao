using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PopupGuildWarFocus : CaocaoUIModalPopup
{
	[SerializeField]
	private Transform TargetGuild;

	[SerializeField]
	private Transform ColorSetting;

	[SerializeField]
	private CaocaoTabButtons ColorChangeTabButtons;

	[SerializeField]
	private UIGrid FocusedGuilds;

	private int ColorPalette;

	private int colorSetCount;

	private static PopupGuildWarFocus _self;

	private ulong targetGuildId;

	private Action _callback;

	private List<bool> guildInfoRefreshFlag;

	private Transform lastSelectedFocusedItem;

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

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public static void Show(ulong targetGuildId, Action callback)
	{
	}

	private void RefreshGuildInfos(JsonData jsonData)
	{
	}

	public void initData()
	{
	}

	private void OnColorTabChanged(CaocaoButton button)
	{
	}

	private void ColorSettingChange()
	{
	}

	private void UpdateColorSettingCheck()
	{
	}

	private void UpdateTargetGuildUi()
	{
	}

	private void UpdateGuildInfo(Transform guildInfoTransfom, ulong guildId)
	{
	}

	private void GuildinfoLabelUpdate(Transform guildInfoTransfom, string name, int emblemIndex, int rank)
	{
	}

	private void UpdateFocusedGuilds()
	{
	}

	public void OnClickBtnClose()
	{
	}

	public void OnClickBtnSave()
	{
	}

	public void OnClickBtnRevert()
	{
	}
}
