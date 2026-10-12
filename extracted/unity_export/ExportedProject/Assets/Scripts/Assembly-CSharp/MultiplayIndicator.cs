using System;
using UnityEngine;

public class MultiplayIndicator : SceneSingleton<MultiplayIndicator>
{
	private GameObject indicator;

	private UILabelLocalized descLabel;

	private float m_startSec;

	private float m_expireSec;

	private Action<float> m_expireCallback;

	public bool activated
	{
		get
		{
			return false;
		}
	}

	protected MultiplayIndicator()
	{
	}

	public void On(string desc, float expire = -1f, Action<float> expireCallback = null)
	{
	}

	public void Off(bool setExpire = false)
	{
	}

	private void OnUserRegisteredCallback()
	{
	}

	private void Update()
	{
	}

	protected override void OnDestroy()
	{
	}
}
