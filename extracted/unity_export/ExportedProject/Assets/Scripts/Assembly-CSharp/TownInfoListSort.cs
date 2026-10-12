using System;
using UnityEngine;

public class TownInfoListSort : SelfRegister2UIComponentsMonobehaviour
{
	private readonly EnumData.TownSortType[] IncludesSortTypes;

	[SerializeField]
	private UIGrid m_grid;

	private bool m_initiallyEnabled;

	protected override void Awake()
	{
	}

	private void Start()
	{
	}

	private void OnClick()
	{
	}

	public void Show(Action<EnumData.TownSortType> callback)
	{
	}

	public void Close()
	{
	}

	private void RemoveUnitList()
	{
	}
}
