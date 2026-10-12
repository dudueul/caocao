using System;
using System.Collections.Generic;
using UnityEngine;

public class CommonRewardGridPanel : MonoBehaviour
{
	public UIButton m_btnConfirm;

	public UIGrid m_grid;

	private List<GameObject> m_items;

	public Action OnBtnConfirmClicked;

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	public void AddItem(GameObject item)
	{
	}

	public void DeleteItem(GameObject item)
	{
	}

	public void Reposition()
	{
	}

	public void Clear()
	{
	}

	public void OnConfirmClicked()
	{
	}
}
