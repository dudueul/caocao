using System.Collections.Generic;
using UnityEngine;

public class PanelGuildWarLobby : PanelSelfRegister2UIComponentsMonobehaviour
{
	public const string popupName = "PanelGuildWarLobby";

	[SerializeField]
	private UIButton btn_declearWar;

	[SerializeField]
	private UILabel GuildActivePoint;

	[SerializeField]
	private UILabel MyActivePoint;

	[SerializeField]
	private UILabel GuildAvgActivePoint;

	[SerializeField]
	private UILabelLocalized NationName;

	[SerializeField]
	private GameObject GuildTier;

	[SerializeField]
	private UIButton btn_GuildSetting;

	[SerializeField]
	private UILabel GuildName;

	[SerializeField]
	private UITexture FaceTexture;

	[SerializeField]
	private UILabel GuildWarFood;

	[SerializeField]
	private GameObject GuildWarMark;

	[SerializeField]
	private UIGrid GuildWarLogs;

	[SerializeField]
	private GameObject GuildSetting;

	[SerializeField]
	private UILabel WaitTime;

	private const float requestIntervalGetLobbyData = 60f;

	private bool m_invalidButtonDeclare;

	private bool m_invalidBattleHistories;

	private bool m_invalidActivityPoint;

	private bool m_invalidGuildAndNation;

	protected override void Awake()
	{
	}

	public static void Show()
	{
	}

	public override void ShowPanel()
	{
	}

	private void UpdateData()
	{
	}

	public override void ClosePanel()
	{
	}

	private void OnScheduleDeserialized(HashSet<GuildWarSchedule> datas)
	{
	}

	private void OnBattleHistoryDeserialized(HashSet<GuildWarBattleHistoryInfo> datas)
	{
	}

	private void OnGuildActivityPointDeserialied(HashSet<GuildWarRankPoint> datas)
	{
	}

	private void OnUserBaseDatasDeserialized(HashSet<GuildWarUserBaseData> datas)
	{
	}

	public void ShowLoadingError()
	{
	}

	private void UpdateSeasonTime()
	{
	}

	private void InvalidateButtonDeclare()
	{
	}

	private void UpdateButtonDeclare()
	{
	}

	private void InvalidateBattleHistories()
	{
	}

	private void UpdateBattleHistories()
	{
	}

	private void InvalidateActivityPoint()
	{
	}

	private void UpdateActivityPoint()
	{
	}

	private void InvalidateGuildAndNation()
	{
	}

	private void UpdateGuildAndNation()
	{
	}

	private void InvalidateUIAll()
	{
	}

	private void UpdateUIAll()
	{
	}

	private void Update()
	{
	}

	private void HistoryLogObjectUpdate(Transform historyLogObj, GuildWarBattleHistoryInfo guildWarBattleHistoryInfo)
	{
	}

	public void BtnGuildSetting()
	{
	}

	public void OnSettingBtnClicked()
	{
	}

	public void GotoWorldMap()
	{
	}

	public void OnClickRanking()
	{
	}

	public void OnClickReward()
	{
	}

	public void OnClickHelp()
	{
	}

	public void OnClickArmory()
	{
	}

	public void OnClickMap()
	{
	}
}
