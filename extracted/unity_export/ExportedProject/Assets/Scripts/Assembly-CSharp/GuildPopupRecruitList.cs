using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class GuildPopupRecruitList : CaocaoUIModalPopup
{
	public UIScrollView gridScrollView;

	public UIGrid itemGrid;

	public UIInput inputSearch;

	public CaocaoTabButtons tabButtons;

	public Color fullMemberColor;

	public Color ableMemberColor;

	public Color ableGuestColor;

	private EnumData.GuildAcceptType currentTab;

	private List<GuildUIRecruitListItem> listItems;

	private ulong detailInfoGuildId;

	private Coroutine updateGuildListCoroutine;

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

	protected override void Awake()
	{
	}

	public static void Show()
	{
	}

	protected void OnGuildError(string error, ref bool handled)
	{
	}

	protected void UpdateGuildList(List<GuildInfo> list)
	{
	}

	protected void UpdatedMemberList()
	{
	}

	protected void UpdateMe()
	{
	}

	private void RemoveAll()
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateGuildListCoroutine(List<GuildInfo> list)
	{
		return null;
	}

	private void UpdateApplyButtons()
	{
	}

	private void RequestDetailInfo(GuildUIRecruitListItem item)
	{
	}

	private void RequestJoin(GuildUIRecruitListItem item)
	{
	}

	private void RequestCancelJoin(GuildUIRecruitListItem item)
	{
	}

	private void UpdateGuildOwner(GuildUIRecruitListItem listItem, GuildInfo guild)
	{
	}

	public void SwitchTab(CaocaoButton tabButton)
	{
	}

	public void RefreshList()
	{
	}

	public void OnSearchClicked()
	{
	}

	protected override void OnDestroy()
	{
	}
}
