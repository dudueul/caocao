using UnityEngine;

public class ArenaSynergyTypeUI : MonoBehaviour
{
	private UISprite m_activeIcon;

	private UISprite m_deactiveIcon;

	private int m_arenaSynergyType;

	private bool m_showUnits;

	private bool m_activate;

	private bool clickable;

	public UISprite activeIcon
	{
		get
		{
			return null;
		}
	}

	public UISprite deactiveIcon
	{
		get
		{
			return null;
		}
	}

	public int arenaSynergyType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool showUnits
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool activate
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private void UpdateIcon()
	{
	}

	private void UpdateButton()
	{
	}

	private void UpdateActivate()
	{
	}

	public void SetEnableButton(bool enable)
	{
	}

	private void OnDestroy()
	{
	}
}
