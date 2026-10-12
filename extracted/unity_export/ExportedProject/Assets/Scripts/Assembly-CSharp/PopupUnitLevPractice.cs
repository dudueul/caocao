using System;
using System.Collections;
using System.Diagnostics;

public class PopupUnitLevPractice : PanelPopup2Buttons
{
	private int m_gaugeTo;

	private int m_gaugeBeforMax;

	private UserUnit m_userUnit;

	public int m_needStamp;

	private bool m_wait;

	public Action OnUpdatedUnitLev;

	private int m_beforeExp;

	protected override void OnSetContext()
	{
	}

	public void Apply(UserUnit userUnit)
	{
	}

	private void UnitLevPractice()
	{
	}

	public void OnCompleteProgressToMax()
	{
	}

	[DebuggerHidden]
	private IEnumerator Waiting()
	{
		return null;
	}

	public void OnCompleteProgressToNextExp()
	{
	}
}
