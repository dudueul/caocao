using System.Collections.Generic;
using UnityEngine;

public class GuildPopupRecruitInfo : CaocaoUIModalPopup
{
	public GuildUIRecruitListItem guildInfoItem;

	public UILabelLocalized labelRecruitMsg;

	public GameObject btnApply;

	public GameObject btnCancel;

	private GuildInfo m_guild;

	private List<GuildMember> listMember;

	protected override void Awake()
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	private void OnUpdateMe()
	{
	}

	private void OnGuildError(string error, ref bool handled)
	{
	}

	private void UpdateApplyButton()
	{
	}

	public void OnChatClicked()
	{
	}

	public void OnShowMembers()
	{
	}

	public void OnApply()
	{
	}

	public void OnCancelApply()
	{
	}

	public static void Show(GuildInfo guild, Dictionary<ulong, GuildMember> members)
	{
	}

	protected override void OnDestroy()
	{
	}
}
