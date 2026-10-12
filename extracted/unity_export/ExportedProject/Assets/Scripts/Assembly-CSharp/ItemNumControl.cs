using System;
using UnityEngine;

public class ItemNumControl : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized curNum;

	[SerializeField]
	private UILabelLocalized minNum;

	[SerializeField]
	private UILabelLocalized maxNum;

	[SerializeField]
	private UIButton btnMinus;

	[SerializeField]
	private UIButton btnPlus;

	[SerializeField]
	private UISlider slider;

	private Action<int> updateHandler;

	public int minValue { get; private set; }

	public int maxValue { get; private set; }

	public int curValue { get; private set; }

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	public void UpdateUI(int minNum, int maxNum, int curNum, bool forceUpdate = false)
	{
	}

	public void UpdateValue(int num, bool forceUpdate = false)
	{
	}

	private void UpdateMinMax(int min, int max)
	{
	}

	private void UpdateSlider(int updateValue)
	{
	}

	private void OnSliderChanged()
	{
	}

	public void OnPlus(int count)
	{
	}

	public void OnMinus(int count)
	{
	}

	public void AddOnUpdatedHandler(Action<int> callback)
	{
	}
}
