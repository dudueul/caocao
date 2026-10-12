using System;
using UnityEngine;

public class UIStatProgressBar : MonoBehaviour
{
	private static Color ms_buffColor;

	private static Color ms_debuffColor;

	public Color32 baseColor;

	public int max;

	public int baseValue;

	public int buffValue;

	[HideInInspector]
	public Action OnUpdateProgressLabel;

	[HideInInspector]
	public Action OnUpdateTotalLabel;

	[HideInInspector]
	public Action OnCompleteProgress;

	private UILabelLocalized m_progressValueLabel;

	private UILabelLocalized m_currentValueLabel;

	private UISprite m_spriteAbove;

	private UISprite m_spriteBelow;

	[HideInInspector]
	public float animatedBaseValue;

	[HideInInspector]
	public float animatedBuffValue;

	private Transform m_currValue;

	private Transform m_nextValue;

	private Transform m_arrow;

	public int current
	{
		get
		{
			return 0;
		}
	}

	public UILabelLocalized progressValueLabel
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized currentValueLabel
	{
		get
		{
			return null;
		}
	}

	public UISprite spriteAbove
	{
		get
		{
			return null;
		}
	}

	public UISprite spriteBelow
	{
		get
		{
			return null;
		}
	}

	public Transform currValue
	{
		get
		{
			return null;
		}
	}

	public Transform nextValue
	{
		get
		{
			return null;
		}
	}

	public Transform arrow
	{
		get
		{
			return null;
		}
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	public void SetData(int baseValue, int buffValue)
	{
	}

	public void SetNextData(int value)
	{
	}

	private void HideCompareUI()
	{
	}

	public void SetDataImmediately(int baseValue, int buffValue)
	{
	}

	private void AnimateProgress()
	{
	}

	private void OnComplete()
	{
	}

	private void OnBaseUpdated(float value)
	{
	}

	private void OnBuffUpdated(float value)
	{
	}

	private void UpdateProgress()
	{
	}

	public void UpdateTroopsStatProgressLabel()
	{
	}

	public void UpdateTroopsStatTotalLabel()
	{
	}

	public void UpdateUnitStatProgressLabel()
	{
	}
}
