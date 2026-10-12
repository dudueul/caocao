using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;

public class GaugeAnimation : MonoBehaviour
{
	[StructLayout((LayoutKind)0, Size = 12)]
	private struct AnimValues
	{
		public int from;

		public int to;

		public int max;

		public AnimValues(int f, int t, int m)
		{
			from = 0;
			to = 0;
			max = 0;
		}
	}

	private UISlider m_slider;

	private UILabelLocalized m_labelValue;

	private int m_fromValue;

	private int m_toValue;

	private int m_maxValue;

	private int m_adjustedToValue;

	private float m_prevSliderValue;

	private bool m_playing;

	private bool m_completed;

	private string tweenName;

	private List<AnimValues> animValues;

	private int currentAnimIdx;

	public bool isPlaying
	{
		get
		{
			return false;
		}
	}

	public bool isCompleted
	{
		get
		{
			return false;
		}
	}

	private void OnDestroy()
	{
	}

	public void AddAnimData(UISlider slider, UILabelLocalized labelValue, int from, int to, int max)
	{
	}

	public void StartAnimation(int dataIdx)
	{
	}

	private void GaugeAnimationStarted()
	{
	}

	private void GaugeAnimationUpdated(float value)
	{
	}

	private void GaugeAnimationCompleted()
	{
	}

	private void SetValues(float value)
	{
	}
}
