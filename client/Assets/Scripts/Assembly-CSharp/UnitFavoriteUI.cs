using System;
using UnityEngine;

public class UnitFavoriteUI : MonoBehaviour
{
	private UserUnit m_userUnit;

	public GameObject m_selected;

	public GameObject m_notSelected;

	public GameObject m_OutSelected;

	public Action<UnitFavoriteUI> OnClickHandler;

	public UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void UpdateUI()
	{
	}

	public void OnClick()
	{
	}
}
