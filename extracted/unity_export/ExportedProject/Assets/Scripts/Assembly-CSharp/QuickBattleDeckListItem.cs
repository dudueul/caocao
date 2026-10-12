using System.Collections.Generic;
using UnityEngine;

public class QuickBattleDeckListItem : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized m_slotNum;

	[SerializeField]
	private UILabelLocalized m_deckName;

	[SerializeField]
	private UIInput m_dekcNameInput;

	[SerializeField]
	private UIGrid m_grid;

	[SerializeField]
	private UIButton m_assign;

	[SerializeField]
	private UILabelLocalized m_price;

	[SerializeField]
	private UIButton m_purchase;

	[SerializeField]
	private Transform m_dim;

	[SerializeField]
	private List<QuickBattleDeckUnitFaceUI> faceUIList;

	private List<UserQuickBattleDeckUnit> deckUnits;

	private UserQuickBattleDeck m_deck;

	private EnumData.QuickBattleDeckAction currentAction;

	private PopupQuickBattleDeck m_parent;

	private int m_slotNumber;

	public void Initialize(PopupQuickBattleDeck parent)
	{
	}

	public void UpdateUI(UserQuickBattleDeck deck, EnumData.QuickBattleDeckAction action)
	{
	}

	public void UpdateUI(int slotNum, EnumData.BattleMode mode, EnumData.QuickBattleDeckAction action)
	{
	}

	private void UpdateBattleFieldDefenceDeckGrid(UserQuickBattleDeck deck)
	{
	}

	private void UpdateDeckGrid(UserQuickBattleDeck deck)
	{
	}

	public void Assign()
	{
	}

	private void AssignBattleFieldDefenseMode()
	{
	}

	private void AssignBattleDeck()
	{
	}

	public void Purchase()
	{
	}

	private void OnPurchaseCallback(bool succeed, UserQuickBattleDeck newDeck)
	{
	}

	public void OnSubmit()
	{
	}

	private void OnDestroy()
	{
	}
}
