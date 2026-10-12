using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class NumberRotate : MonoBehaviour
{
	[SerializeField]
	private UILabel _label;

	[SerializeField]
	private float _startDelaySec;

	[SerializeField]
	private float _completeTimeSec;

	[SerializeField]
	private int _decimalPoint;

	private float _startValue;

	private float _lastValue;

	private string _decimalFormat;

	private float _decimalConst;

	public void SetValue(float start, float last, int decimalPoint = -1, float startDelaySec = -1f, float completeTimeSec = -1f)
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateText()
	{
		return null;
	}

	private void SetText(float value)
	{
	}

	private float GetValue(float _start, float _end, float _distance)
	{
		return 0f;
	}
}
