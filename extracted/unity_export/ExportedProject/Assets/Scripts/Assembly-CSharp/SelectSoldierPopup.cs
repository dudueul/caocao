using System;
using UnityEngine;

public class SelectSoldierPopup : CaocaoUIModalPopup
{
	private UIGrid m_grid;

	private int m_deckId;

	private int m_slotId;

	private int m_soldierId;

	private Action<int> onClickSoldier__BackingField;

	private event Action<int> onClickSoldier
	{
		add
		{
		}
		remove
		{
		}
	}

	protected override void Awake()
	{
	}

	public void SetData(int deckId, int slotId, int soldierId, Action<int> callback)
	{
	}

	private void InitSoldierList()
	{
	}

	private void SetSoldierList()
	{
	}

	private void UpdateGrid(Transform gridTransform, Soldier soldier, int soldierLevel)
	{
	}

	private void SetGrid()
	{
	}

	private void SelectSoldier(int soldierId)
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
