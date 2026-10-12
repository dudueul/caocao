using System;
using UnityEngine;

public class UIGuildWarPassiveGridItem : MonoBehaviour
{
	[SerializeField]
	private GameObject selectGameObject;

	private bool m_selected;

	public Action<UIGuildWarPassiveGridItem> onClicked;

	private UIGuildWarPassiveData m_passiveData;

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

	public UIGuildWarPassiveData passiveData
	{
		get
		{
			return null;
		}
	}

	private void Start()
	{
	}

	private void Update()
	{
	}

	public void OnClicked()
	{
	}

	public void UpdateUI(UIGuildWarPassiveData passiveData)
	{
	}
}
