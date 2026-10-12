using System;
using UnityEngine;

[ExecuteInEditMode]
public class SkillWithNameUI : MonoBehaviour
{
	private int m_skillId;

	private bool m_isSelected;

	private bool m_isScrollable;

	public Action<SkillWithNameUI> OnClickHandler;

	public Action<SkillWithNameUI> OnDoubleClickHandler;

	public Action<SkillWithNameUI> OnSelectHandler;

	public Action<SkillWithNameUI> OnPressHandler;

	public Action<SkillWithNameUI> OnReleaseHandler;

	private int skillId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public Skill skillData { get; private set; }

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

	private bool IsValid()
	{
		return false;
	}

	public void SetSkillId(int id)
	{
	}

	private void UpdateSkillUI()
	{
	}

	private void UpdateSkillName()
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
