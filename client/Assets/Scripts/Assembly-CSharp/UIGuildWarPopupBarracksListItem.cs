using System.Collections.Generic;
using GuildWar;
using UnityEngine;

public class UIGuildWarPopupBarracksListItem : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized m_deckName;

	[SerializeField]
	private UILabelLocalized m_typeComment;

	[SerializeField]
	private UIGrid m_faceGrid;

	[SerializeField]
	private UILabel m_powerValue;

	[SerializeField]
	private List<UIGuildWarBarracksUnitFace> faceUIList;

	private List<UserQuickBattleDeckUnit> deckUnits;

	public static int MaxDefUnitSlotNum;

	private GuildWarBarracksType currentType;

	private UIGuildWarPowerManagement m_parent;

	public List<UserQuickBattleDeckUnit> getDeckUnits
	{
		get
		{
			return null;
		}
	}

	public void Initialize(UIGuildWarPowerManagement parent)
	{
	}

	private int GetStat(int unitid)
	{
		return 0;
	}

	private void SetStatGague(int currentValue)
	{
	}

	private void SetBarracksName()
	{
	}

	public void UpdateUI(List<UserQuickBattleDeckUnit> newDeckUnits, GuildWarBarracksType type)
	{
	}

	private void UpdateBarracksDeckGrid(List<UserQuickBattleDeckUnit> newDeckUnits)
	{
	}

	private void ModifyExecution()
	{
	}

	public void OnModify()
	{
	}

	private void OnDestroy()
	{
	}
}
