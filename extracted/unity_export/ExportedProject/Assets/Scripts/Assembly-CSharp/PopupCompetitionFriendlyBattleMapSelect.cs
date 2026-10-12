using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PopupCompetitionFriendlyBattleMapSelect : CaocaoUIModalPopup
{
	private const int Random_BattleMapId = 0;

	private static PopupCompetitionFriendlyBattleMapSelect self;

	[SerializeField]
	private UIGrid gridBattleMap;

	private Dictionary<int, PvpMapInfoListItem> dicMapInfo;

	private string roomId;

	private int selectedMapId;

	public static void Show(string matchingCode, int selectedMapId = 0)
	{
	}

	public static void Close()
	{
	}

	private void UpdateUI()
	{
	}

	public void OnBtnConfirm()
	{
	}

	private void OnSelecteMap(int mapId)
	{
	}

	public void OnSocketCallback(JsonData jsonData)
	{
	}

	public void OnBtnCancel()
	{
	}

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
}
