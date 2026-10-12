using System.Collections.Generic;
using GuildWar;
using LitJson;
using UnityEngine;

public class GuildWarBattleHistory : MonoBehaviour
{
	private enum BattleHistoryColumns
	{
		GuildName = 0,
		NationName = 1,
		Kill = 2,
		Death = 3,
		Occupation = 4,
		Destroy = 5,
		Contribution = 6,
		UsedGold = 7,
		COUNT = 8
	}

	private struct BattleHistoryColumn
	{
		public UIButton m_btn;

		public GameObject m_up;

		public GameObject m_down;

		public GameObject m_activate;
	}

	private BattleHistoryColumn[] m_battleHistoryColumns;

	private List<GuildWarBattleHistoryListItem.CellData> m_userHistories;

	private BattleHistoryColumns m_activatedHistoryColumn;

	private bool[] m_historySortAscendings;

	private bool m_needToUpdateHistoryUI;

	private bool m_needToUpdateHistoryItems;

	private UIReuseGrid m_battleHistoryGrid;

	public void InitData()
	{
	}

	public void SetData(List<GuildWar.GuildData> guildDatas)
	{
	}

	public void SetData(JsonData guildDatas)
	{
	}

	public void OnBtnHistoryColumn(int columnIndex)
	{
	}

	public void UpdateUI()
	{
	}

	private void UpdateHistoryUI()
	{
	}

	private void UpdateHistoryItems()
	{
	}

	public void DestroyUI()
	{
	}
}
