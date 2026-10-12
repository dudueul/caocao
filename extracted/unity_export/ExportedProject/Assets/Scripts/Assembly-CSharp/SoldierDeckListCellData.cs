using System;
using UnityEngine;

public class SoldierDeckListCellData : MonoBehaviour
{
	private int m_deckId;

	private int m_soldierId;

	private int m_slotId;

	private Action<int, int> onClickSoldier__BackingField;

	private Action<int> onSelectDeck__BackingField;

	private event Action<int, int> onClickSoldier
	{
		add
		{
		}
		remove
		{
		}
	}

	private event Action<int> onSelectDeck
	{
		add
		{
		}
		remove
		{
		}
	}

	public void SetInfo(int deckId, int soldierId, int slotId, bool isDefault, Action<int, int> callback, Action<int> selectCallback)
	{
	}

	private void SetSoldier()
	{
	}

	private void SetCallback(Action<int, int> callback)
	{
	}

	private bool IsLimitLevelForTraining(int soldierLevel)
	{
		return false;
	}

	private void ShowSelectSoldier(Transform tf)
	{
	}

	private void ShowEffect()
	{
	}

	private void OnSelectSoldier(int soldierId)
	{
	}
}
