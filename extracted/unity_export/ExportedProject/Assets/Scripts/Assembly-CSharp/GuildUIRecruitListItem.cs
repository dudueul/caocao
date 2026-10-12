using System;
using System.Collections.Generic;
using UnityEngine;

public class GuildUIRecruitListItem : MonoBehaviour
{
	public class GuildListItemCellData : IReuseCellData
	{
		public int Index { get; set; }

		public GuildInfo guildInfo { get; set; }
	}

	public static Color fullMemberColor;

	public static Color ableMemberColor;

	public static Color ableGuestColor;

	public GameObject objFreeBg;

	public GameObject objReviewBg;

	public GameObject objImpossibleBg;

	public UITexture texMark;

	public UILabelLocalized labelLevel;

	public UILabelLocalized labelName;

	public UILabelLocalized labelOwner;

	public GameObject objPerson;

	public UILabelLocalized labelRankLimit;

	public UILabelLocalized labelPersonNum;

	public UIButton btnDetailInfo;

	public UIButton btnRequest;

	public UIButton btnCancel;

	private GuildListItemCellData m_listItemCellData;

	public int Index
	{
		get
		{
			return 0;
		}
	}

	public ulong guildId
	{
		get
		{
			return 0uL;
		}
	}

	public GuildInfo guildInfo
	{
		get
		{
			return null;
		}
	}

	public void Initiate(int index, GuildInfo guildInfo, Action<GuildUIRecruitListItem> onDetailInfo, Action<GuildUIRecruitListItem> onJoin, Action<GuildUIRecruitListItem> onCancelJoin)
	{
	}

	public void InitiateViewer(GuildInfo guildInfo, List<GuildMember> listMember)
	{
	}

	public string GetOwnerName()
	{
		return null;
	}

	public void SetOwnerName(string owner)
	{
	}

	public void SetApplyButton(bool applied)
	{
	}

	private void UpdateUI()
	{
	}

	private void OnDestroy()
	{
	}
}
