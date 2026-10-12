using System;
using UnityEngine;

public class UnitTreeTabListItemUI : MonoBehaviour
{
	private int m_unitTreeID;

	private UnitTree m_unitTreeData;

	public Action<int> OnClickHandler;

	public int unitTreeID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public void UpdateUI()
	{
	}

	private bool IsValid()
	{
		return false;
	}

	public void OnClick()
	{
	}
}
