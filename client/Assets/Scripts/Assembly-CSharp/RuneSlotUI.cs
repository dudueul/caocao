using System;
using UnityEngine;

public class RuneSlotUI : MonoBehaviour
{
	public PrefabInstance runeUIInst;

	public PrefabInstance passiveUIInst;

	private RuneUI m_runeUI;

	private RunePassiveUI m_runePassiveUI;

	private UserRuneSlot m_userRuneSlot;

	private RuneItemSlot m_runeItemSlot;

	private UserRuneItem m_userRuneItem;

	protected RuneUI runeUI
	{
		get
		{
			return null;
		}
	}

	protected RunePassiveUI runePassiveUI
	{
		get
		{
			return null;
		}
	}

	protected UserRuneSlot userRuneSlot
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool isSelected
	{
		set
		{
		}
	}

	public bool isScrollable
	{
		set
		{
		}
	}

	public bool isNone
	{
		set
		{
		}
	}

	public Action<RuneUI> OnRuneClickHandler
	{
		set
		{
		}
	}

	public Action<SpecialStateUI> OnPassiveClickHandler
	{
		set
		{
		}
	}

	public Action<RuneSlotUI> OnClickHandler
	{
		set
		{
		}
	}

	public Action<RuneSlotUI> OnDoubleClickHandler
	{
		set
		{
		}
	}

	public void SetDim(bool dim)
	{
	}

	public void SetWearing(bool wearing)
	{
	}

	public void SetRuneItemSlot(UserRuneSlot userRuneSlot, RuneItemSlot runeItemSlot)
	{
	}

	public void SetUserRuneItem(UserRuneItem userRuneItem)
	{
	}

	public void SetUserUnitRune(UserUnit userUnit, int runeSlotNum)
	{
	}

	public void SetNoneUI()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	public void ShowRuneUIDesc(bool showDesc)
	{
	}

	public void ShowRunePassiveUIDesc(bool showDesc)
	{
	}

	public void UpdateUI()
	{
	}

	protected virtual void UpdateRuneUI()
	{
	}

	protected virtual void UpdatePassiveUI()
	{
	}

	public void SetActive(bool activate)
	{
	}
}
