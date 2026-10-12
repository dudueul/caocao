using System;
using UnityEngine;

public class ClickFlowChartItem : MonoBehaviour
{
	public int scenarioID;

	public Action<int> OnClicked;

	public Action<int> OnDoubleClicked;

	private float lastClickTime;

	private bool firstClickOccured;

	private void OnDestroy()
	{
	}

	private void OnClick()
	{
	}

	private void DoubleClicked()
	{
	}

	private void OnDoubleClick()
	{
	}
}
