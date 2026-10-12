using System;
using UnityEngine;

[ExecuteInEditMode]
public class SkillUI : MonoBehaviour
{
	private const string ms_frameUsableTrue = "itembox_n";

	private const string ms_frameUsableFalse = "itembox_p";

	private UISprite m_skillIcon;

	private bool m_isSelected;

	private bool m_isScrollable;

	private bool m_available;

	public Action<SkillUI> OnClickHandler;

	public Action<SkillUI> OnDoubleClickHandler;

	public Action<SkillUI> OnSelectHandler;

	public Action<SkillUI> OnPressHandler;

	public Action<SkillUI> OnReleaseHandler;

	public EnumData.Skills skillId
	{
		get
		{
			return EnumData.Skills.NONE;
		}
		set
		{
		}
	}

	public Skill skillData { get; private set; }

	private UISprite skillIcon
	{
		get
		{
			return null;
		}
	}

	public bool isSelected
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isScrollable
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isAvailable
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private bool IsValid()
	{
		return false;
	}

	public void UpdateSkillUsableState(bool usable)
	{
	}

	private void UpdateSkillIcon()
	{
	}

	private void OnDestroy()
	{
	}

	public void OnClick()
	{
	}

	public void OnDoubleClick()
	{
	}

	public void OnSelect()
	{
	}

	public void OnPress()
	{
	}

	public void OnRelease()
	{
	}
}
