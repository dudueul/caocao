using System;
using LitJson;
using UnityEngine;

public class PanelSoldierDeck : MonoBehaviour
{
	private UIGrid m_grid;

	private SoldierDeckListCell.UIType m_uiType;

	private Action onClickAssignDeck__BackingField;

	public SoldierDeckListCell.UIType uiType
	{
		get
		{
			return SoldierDeckListCell.UIType.view_assign;
		}
		set
		{
		}
	}

	public event Action onClickAssignDeck
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

	private void InitPage()
	{
	}

	private void InitList()
	{
	}

	private void SetDeckList()
	{
	}

	private void InsertSoldierDeck(UserSoldierDeck deck)
	{
	}

	private void DefaultSoldierDeck()
	{
	}

	private void ClearGrid()
	{
	}

	public void UpdateUI()
	{
	}

	private void OnSelectItem(UserSoldierDeck deck)
	{
	}

	private void OnAddedSlot(JsonData response)
	{
	}

	private void OnClickedAssignDeck(int deckId)
	{
	}

	private void RequestBattleFieldAssignSoldierDeck(int deckId)
	{
	}
}
