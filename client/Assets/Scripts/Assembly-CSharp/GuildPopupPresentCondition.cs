using System;
using UnityEngine;

public class GuildPopupPresentCondition : CaocaoUIModalPopup
{
	public class CellData : GuildUIMemberListObject.GuildMemberCellData
	{
		public int[] num;

		public override string GetCustomLabelText(int idx)
		{
			return null;
		}
	}

	[Serializable]
	public class ListObject : GuildUIMemberListObject
	{
		public GuildItem guildItem;

		public GuildPresent presentData;

		protected override GuildMemberCellData GenerateCellData(int index, GuildMember member)
		{
			return null;
		}

		protected override Func<GuildUIMemberCategory, GuildMemberCellData, GuildMemberCellData, int> GenerateCustomSortFunc()
		{
			return null;
		}
	}

	public ListObject listObject;

	public GameObject tableItemSource;

	public UIGrid gridLimit;

	public ItemUI itemUI;

	public UILabelLocalized labelItemName;

	private bool isGood;

	private int itemId;

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

	public static void Show(bool isGood, int itemId)
	{
	}

	private void UpdateUI()
	{
	}
}
