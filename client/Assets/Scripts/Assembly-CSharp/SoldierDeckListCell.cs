using System;
using LitJson;
using UnityEngine;

public class SoldierDeckListCell : MonoBehaviour
{
	public enum UIType
	{
		view_assign = 0,
		assign_deck = 1
	}

	private UserSoldierDeck m_deckInfo;

	private UIType m_uiType;

	private bool m_isDefault;

	private GameObject m_btnInfo;

	private GameObject m_btnSet;

	private GameObject m_seleted;

	private Action<int> onClickAssignDeck__BackingField;

	private Action<int> onSelectGrid__BackingField;

	private Action<JsonData> onAdded__BackingField;

	public event Action<int> onClickAssignDeck
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<int> onSelectGrid
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<JsonData> onAdded
	{
		add
		{
		}
		remove
		{
		}
	}

	private void Start()
	{
	}

	public void SetDeck(UserSoldierDeck deck, UIType uiType, bool need2Buy = false)
	{
	}

	private void SwichMode(UIType type)
	{
	}

	private void SetSoldierDecks()
	{
	}

	private void SetSodlierData(Transform tf, int deckId, int soldierId, int slotId)
	{
	}

	private void SetDeckInfo()
	{
	}

	private void SetDeckAverageLevel()
	{
	}

	private void SetDeckAverageAttack()
	{
	}

	private void SetAssignedTownInfo()
	{
	}

	private void SetPrice(Transform tf)
	{
	}

	private void SetCost(Transform tf, SoldierSlot slot)
	{
	}

	private void SetPriceIcon(Transform tf, int costType)
	{
	}

	private void ShowArrangeSoldierDeckPopUp()
	{
	}

	public void SetSelect(UserSoldierDeck deck)
	{
	}

	public void OnClickAddDeck()
	{
	}

	public void OnClickAssignSoldierDeck()
	{
	}

	public void OnClickShowAssignedDeck()
	{
	}

	private void OnChangeSoldier(int deckId, int slotId)
	{
	}

	private void OnClickSoldier(int deckId)
	{
	}

	private void OnUpdateSoldierDeckAssign()
	{
	}

	private SoldierSlot GetSoldierSlot()
	{
		return null;
	}

	private int GetSoldierLevel(int soldierId)
	{
		return 0;
	}

	private int GetSoldierBattleAbility(int soldierId)
	{
		return 0;
	}
}
