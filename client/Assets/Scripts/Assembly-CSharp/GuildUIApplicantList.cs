using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class GuildUIApplicantList : GuildUIUserListBase
{
	private bool accepted;

	private string processUserName;

	private List<ulong> listRejectUsers;

	private bool requestApplicants;

	private Coroutine rejectAllCoroutine;

	protected override void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnGuildError(string error, ref bool handled)
	{
	}

	private void OnUpdateApplicants()
	{
	}

	public void OnApplyClicked()
	{
	}

	public void OnRejectClicked()
	{
	}

	public void OnRejectAllClicked()
	{
	}

	[DebuggerHidden]
	private IEnumerator RejectAll()
	{
		return null;
	}

	private void OnDestroy()
	{
	}
}
