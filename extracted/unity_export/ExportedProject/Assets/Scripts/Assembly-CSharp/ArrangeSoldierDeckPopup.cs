using System;
using System.Collections.Generic;
using UnityEngine;

public class ArrangeSoldierDeckPopup : CaocaoUIModalPopup
{
	private UIGrid m_grid;

	private Transform m_worldMap;

	private int m_prevTownId;

	protected override void Awake()
	{
	}

	public void ShowArrangeMap(int deckId)
	{
	}

	private void Init()
	{
	}

	private void GetGrid()
	{
	}

	private void GetWorldmap()
	{
	}

	private void SetArrangedSoldierDeckList(List<int> townList)
	{
	}

	private void SetStateGrid(List<int> townList)
	{
	}

	private void SetTownist(List<int> townList)
	{
	}

	private void SetGridItem(GameObject obj, int arrangeId, Action<int> callBack)
	{
	}

	private void UpdataeGridItem(int townId)
	{
	}

	private void UpdateStateInfo(int townId, bool isActive)
	{
	}

	private void UpdateTownSelect(int townId, bool isActive)
	{
	}

	private void OnSelectTown(int townId)
	{
	}

	private void OnSelectBattleField(int battleFieldId)
	{
	}

	private void SetBattleFieldList()
	{
	}

	private void UpdateBattleFieldSelect(bool isActive)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}
}
