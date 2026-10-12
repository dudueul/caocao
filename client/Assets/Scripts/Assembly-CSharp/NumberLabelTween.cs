using UnityEngine;

public class NumberLabelTween : MonoBehaviour
{
	private string m_animName;

	private string m_labelAnimNamePrefix;

	private UILabelLocalized m_valueLabel;

	private int m_currentValue;

	private int m_toValue;

	private bool m_doCommify;

	private void OnDestroy()
	{
	}

	public void Apply(int currentValue, int toValue, float time, bool doCommify, string targetIdentifier)
	{
	}

	private void OnLabelChangeUpdated(int value)
	{
	}

	private void OnLabelAnimCompleted()
	{
	}

	public void StopTween()
	{
	}
}
