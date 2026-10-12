using System;
using UnityEngine;

public class GoodsUI : MonoBehaviour
{
	private UISprite m_bar;

	private UIButton m_btn;

	private UISprite m_icon;

	private UILabelLocalized m_valueLabel;

	private int m_currentValue;

	private int m_maxValue;

	private int m_toValue;

	private float m_animationTime;

	private bool m_showMax;

	private bool m_initialized;

	public Action OnGoodsBtnClicked;

	private UISprite bar
	{
		get
		{
			return null;
		}
	}

	private UIButton btn
	{
		get
		{
			return null;
		}
	}

	private UISprite icon
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized valueLabel
	{
		get
		{
			return null;
		}
	}

	protected virtual void Start()
	{
	}

	private float GetToValueFillAmount(int toValue, int maxValue)
	{
		return 0f;
	}

	public void Init(int current, int max, float animationTime = 1.5f, bool showMax = false)
	{
	}

	private void InitValue(int toValue)
	{
	}

	public void SetValue(int toValue)
	{
	}

	public void SetValue(int toValue, int maxValue)
	{
	}

	public void SetUnknown()
	{
	}

	public virtual void SetType(EnumData.GoodsType type)
	{
	}

	private void Animate()
	{
	}

	private void OnBarUpdate(float value)
	{
	}

	private void OnValueUpdate(float time)
	{
	}

	private void OnCompleted()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	private void SetValueKey(int value)
	{
	}

	private void OnBtnClicked()
	{
	}
}
