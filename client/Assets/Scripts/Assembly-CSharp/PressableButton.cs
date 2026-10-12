using System;
using UnityEngine;

public class PressableButton : MonoBehaviour
{
	public Action<int> OnPressedLogic;

	private float m_firstPressedTime;

	private float m_logicCallElapsedTime;

	private float m_beforeRealTime;

	private float m_curRealTime;

	private bool m_pressing;

	private bool pressing
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private void OnPress(bool isPressed)
	{
	}

	private void OnRelease()
	{
	}

	private void OnDisable()
	{
	}

	private void Update()
	{
	}
}
