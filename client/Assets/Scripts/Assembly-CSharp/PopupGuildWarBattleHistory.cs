using GuildWar;
using LitJson;

public class PopupGuildWarBattleHistory : CaocaoUIModalPopup
{
	private GuildWarBattleHistory m_battleHistory;

	private static PopupGuildWarBattleHistory _self;

	private int clanDBShard;

	private int worldId;

	private int battleWorldId;

	private int townId;

	private ulong guildId;

	private bool IsOutGame;

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

	protected override void OnDestroy()
	{
	}

	private void Update()
	{
	}

	public static void OutGameShow(int clanDBShard, int worldId, int p_battleWorldId, int p_townId, ulong guildId)
	{
	}

	public void OutGameinitData(JsonData response)
	{
	}

	private void HistoryGridInit()
	{
	}

	public static void Show(WorldData worldData)
	{
	}

	private void initData(WorldData worldData)
	{
	}

	private void UpdateUI()
	{
	}

	private void DestroyUI()
	{
	}

	public void OnClickBtnClose()
	{
	}
}
