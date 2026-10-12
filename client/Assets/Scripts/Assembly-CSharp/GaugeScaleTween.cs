using UnityEngine;

public class GaugeScaleTween : MonoBehaviour
{
	private string m_animationName;

	private UISprite m_targetUI;

	private float m_fromValue;

	private float m_toValue;

	private string m_labelAnimName;

	private UILabelLocalized m_valueLabel;

	private int m_labelToValue;

	private void OnDestroy()
	{
	}

	public void Apply(UISprite targetUI, int currentValue, int toValue, int maxValue, float time = 0.5f)
	{
	}

	public void Apply(UISprite targetUI, UILabelLocalized valueLabel, int currentValue, int toValue, int maxValue, float time = 0.5f)
	{
	}

	private void OnGuageChangeUpdated(float value)
	{
	}

	private void OnLabelChangeUpdated(int value)
	{
	}

	private void OnLabelAnimCompleted()
	{
	}
}
