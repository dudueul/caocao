using System;
using System.Collections.Generic;
using UnityEngine;

public class CaocaoTabButtons : MonoBehaviour
{
	public enum Side
	{
		UP = 0,
		DOWN = 1,
		LEFT = 2,
		RIGHT = 3
	}

	[SerializeField]
	private Side tabSide;

	[SerializeField]
	private float offset;

	[SerializeField]
	private bool includeInactive;

	public Func<CaocaoButton, bool> OnCheckTabChange;

	public Action<CaocaoButton> OnTabChanged;

	private List<CaocaoButton> m_buttons;

	public List<CaocaoButton> buttons
	{
		get
		{
			return null;
		}
	}

	public CaocaoButton selected { get; private set; }

	public bool CanSelectRepeatedly { get; set; }

	public bool isInactivedAll
	{
		get
		{
			return false;
		}
	}

	private void Awake()
	{
	}

	private void InitButtons()
	{
	}

	public void ForceInitializeButtons()
	{
	}

	private void OnDestroy()
	{
	}

	private void TabButtonSelected(CaocaoButton button)
	{
	}

	public void SelectTab(string buttonName)
	{
	}

	public void SelectTab(int index)
	{
	}

	public void ClearSelection()
	{
	}

	private void RepositionButton(CaocaoButton button, float deltaPos)
	{
	}

	public void SetActiveButtons(bool active)
	{
	}

	public void SetActiveButton(string btnName, bool active)
	{
	}

	public CaocaoButton GetButton(string btnName)
	{
		return null;
	}
}
