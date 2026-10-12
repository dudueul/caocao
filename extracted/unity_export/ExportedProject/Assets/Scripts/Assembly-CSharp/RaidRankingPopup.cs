using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class RaidRankingPopup : CaocaoUIModalPopup
{
	[SerializeField]
	private UIReuseGrid m_grid;

	[SerializeField]
	private UILabelLocalized m_titleLabel;

	private int m_raidType;

	private ulong m_guildId;

	private ulong m_raidId;

	protected List<UserRaidAwakenAttackInfo> m_rankerList;

	public static void Show(int raidType, ulong guildId, ulong raidId)
	{
	}

	private void CreateRankerList()
	{
	}

	public void OnResponseRefreshRankingInfo(JsonData response)
	{
	}

	protected void UpdateUI()
	{
	}

	protected void UpdateGrid()
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
