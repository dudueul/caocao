using System;
using UnityEngine;

internal class PanelCustomEventIngameListItem : MonoBehaviour
{
	private Action<CustomEventIngame, bool> onClick__BackingField;

	private bool m_clearTag;

	private bool m_selected;

	private bool m_newTag;

	public CustomEventIngame eventIngame { get; private set; }

	public bool clearTag
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool selected
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool newTag
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private event Action<CustomEventIngame, bool> onClick
	{
		add
		{
		}
		remove
		{
		}
	}

	public void SetData(CustomEventIngame ingame, Action<CustomEventIngame, bool> callback)
	{
	}

	private void UpdateUI()
	{
	}

	public void OnClickBtn()
	{
	}
}
