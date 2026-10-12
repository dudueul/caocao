using System;
using UnityEngine;

public class SimpleLoadingIndicator : CaocaoUIModalPopup
{
	private static GameObject indicator;

	private UILabelLocalized conditionLabel;

	private UILabelLocalized percentLabel;

	private string coditionText;

	private string footer;

	private float lastUpdatedTime;

	private Action finishCallback;

	public static void Show(Action callback)
	{
	}

	public static void Hide()
	{
	}

	public static void UpdateState(int _stateId)
	{
	}

	private void Update()
	{
	}

	private void UpdateConditionFooter()
	{
	}

	public static void UpdateConditionLabel(string text)
	{
	}

	public static void UpdatePercentText(int percent)
	{
	}

	public void DoFsmFinishCallback()
	{
	}

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
}
