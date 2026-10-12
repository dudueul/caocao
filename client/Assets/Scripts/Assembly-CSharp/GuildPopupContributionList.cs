using System;
using System.Collections.Generic;

public class GuildPopupContributionList : CaocaoUIModalPopup
{
	public class CellData : GuildUIMemberListObject.GuildMemberCellData
	{
		public float contribution;

		public DateTime updDate;

		public override string GetCustomLabelText(int idx)
		{
			return null;
		}
	}

	[Serializable]
	public class ListObject : GuildUIMemberListObject
	{
		[NonSerialized]
		public int missionCount;

		public Dictionary<ulong, GuildMission.Contribution> contributions;

		protected override GuildMemberCellData GenerateCellData(int index, GuildMember member)
		{
			return null;
		}

		protected override Func<GuildUIMemberCategory, GuildMemberCellData, GuildMemberCellData, int> GenerateCustomSortFunc()
		{
			return null;
		}
	}

	public UILabelLocalized labelTime;

	public ListObject listObject;

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

	public static void Show(int missionCount, List<GuildMission.Contribution> listContribution, GuildPopupMission.MissionType missionType)
	{
	}

	protected override void OnDestroy()
	{
	}
}
