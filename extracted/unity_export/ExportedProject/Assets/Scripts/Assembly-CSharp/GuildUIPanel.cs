using System;
using System.Collections.Generic;
using UnityEngine;

public class GuildUIPanel<T> : PanelSelfRegister2UIComponentsMonobehaviour where T : MonoBehaviour
{
	public UITexture texMark;

	public UILabel labelGuildName;

	public UILabelLocalized labelOwnerName;

	public UILabelLocalized labelMemberNum;

	public UILabelLocalized labelApplicantNum;

	public UILabelLocalized labelMyPoint;

	public UILabelLocalized labelExp;

	public UILabelLocalized labelMOTD;

	public UILabelLocalized labelLevel;

	protected static T ms_self;

	public static Action OnShowPanel;

	public static Action OnHidePanel;

	protected virtual void Start()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	protected virtual void OnGuildError(string err, ref bool handled)
	{
	}

	protected virtual void OnUpdateMe()
	{
	}

	private void OnRecruitingGuildList(List<GuildInfo> list)
	{
	}

	protected virtual void OnUpdateMyGuild()
	{
	}

	private void OnMemberListChange()
	{
	}

	protected virtual void UpdateEachMembers()
	{
	}

	protected virtual void OnApplicantListChange()
	{
	}

	protected virtual void UpdateApplicants()
	{
	}

	protected virtual void UpdateGuildList(List<GuildInfo> list)
	{
	}

	protected void ChangeTexture(UITexture tex, string path)
	{
	}

	protected void ChangeLabel(UILabelLocalized label, string text)
	{
	}

	protected void ChangeLabel(UILabelLocalized label, string text, params object[] args)
	{
	}

	protected virtual void OnShow()
	{
	}

	protected virtual void OnHide()
	{
	}

	private static GameObject GetPrefab()
	{
		return null;
	}

	public static bool IsVisible()
	{
		return false;
	}

	public static void Show()
	{
	}

	public static void Hide()
	{
	}
}
