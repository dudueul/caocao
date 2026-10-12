using System;
using UnityEngine;

public class LordEffectUI : MonoBehaviour
{
	private UserUnit m_userUnit;

	private LordEffect m_lordEffect;

	private bool m_autoTooltip;

	private UISprite m_icon;

	private UILabelLocalized m_level;

	public Action<LordEffectUI> OnClickHandler;

	public LordEffect lordEffect
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private UISprite icon
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized level
	{
		get
		{
			return null;
		}
	}

	public void SetLordEffect(UserUnit userUnit, bool autoTooltip = true)
	{
	}

	private void UpdateUI()
	{
	}

	public void OnClick()
	{
	}

	private void OnDestroy()
	{
	}
}
