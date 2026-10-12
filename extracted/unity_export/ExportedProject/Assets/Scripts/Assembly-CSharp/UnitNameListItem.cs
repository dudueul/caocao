using System;
using UnityEngine;

public class UnitNameListItem : MonoBehaviour
{
	private static readonly Color Gradient1TopColor;

	private static readonly Color Gradient1BottomColor;

	private Transform focusTr;

	private UILabelLocalized unitNameLabel;

	private bool m_isSelected;

	public Action<UnitNameListItem> OnClickHandler;

	public Action<UnitNameListItem> OnDoubleClickHandler;

	public Action<UnitNameListItem> OnSelectHandler;

	private bool isDemise;

	private static readonly string noName;

	public UserUnit unit { get; private set; }

	public Unit unitData { get; private set; }

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

	private void OnEnable()
	{
	}

	private void OnDestroy()
	{
	}

	public void SetUserUnit(UserUnit unit)
	{
	}

	private void UpdateUnit()
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
}
