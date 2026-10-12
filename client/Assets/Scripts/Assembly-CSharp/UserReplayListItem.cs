using System;
using UnityEngine;

public class UserReplayListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public ulong srl { get; set; }

		public string battleKey { get; set; }

		public ulong uid { get; set; }

		public string nationName { get; set; }

		public int tierGrade { get; set; }

		public ulong opponent { get; set; }

		public string opponentNationName { get; set; }

		public int opponentFaceUnit { get; set; }

		public int opponentFaceSkinId { get; set; }

		public int opponentTierGrade { get; set; }

		public int opponentPoint { get; set; }

		public DateTime time { get; set; }

		public bool win { get; set; }

		public int recommand { get; set; }

		public EnumData.UserInfoOpenRange openRange { get; set; }
	}

	[SerializeField]
	private GuildUIMarkName guildUI;

	[SerializeField]
	private UILabelLocalized labelName;

	[SerializeField]
	private UITexture texFace;

	[SerializeField]
	private UILabelLocalized labelHour;

	[SerializeField]
	private UITexture texTier;

	[SerializeField]
	private UILabelLocalized labelTier;

	[SerializeField]
	private UITexture texBattleMap;

	[SerializeField]
	private UILabelLocalized labelResult;

	[SerializeField]
	private UILabelLocalized labelRecommanded;

	[SerializeField]
	private UILabelLocalized labelOpenType;

	[SerializeField]
	private UIButton btnSetting;

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public void OnBtnUser()
	{
	}

	public void OnBtnInfo()
	{
	}

	public void OnBtnReplay()
	{
	}

	public void OnBtnSetting()
	{
	}
}
