using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class UIThousandBattleRecoveryButton : MonoBehaviour
{
	public UILabelLocalized remainingRecoveryTimeLabel;

	public UIButton recoverButton;

	public UISprite recoverSprite;

	public UILabelLocalized recoveryCountLabel;

	private Action m_onRecover;

	public void SetRecoverHandler(Action onRecover)
	{
	}

	public void UpdateRecoveryCount()
	{
	}

	private void UpdateRecoveryCountThousandBattle()
	{
	}

	private void UpdateRecoveryCountRaid()
	{
	}

	public void Recover()
	{
	}

	private void ProcessRecoverThousandBattle()
	{
	}

	private void ProcessRecoverRaid()
	{
	}

	[DebuggerHidden]
	private IEnumerator PlayRecoveryCompleteEffect()
	{
		return null;
	}
}
