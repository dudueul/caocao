using System.Collections.Generic;
using GuildWar;
using UnityEngine;

public class FinishGuildWar : FinishBase
{
	private struct ResultGuildList
	{
		public GameObject m_info;

		public UILabel m_labelGuildName;

		public UITexture m_textureGuildEmblem;

		public UISprite m_textureGuildEmblemSprite;

		public GameObject m_attack;

		public GameObject m_defense;

		public GameObject m_dim;
	}

	private bool m_uiInited;

	private UIButton m_btnClose;

	private UIButton m_btnBattleHistory;

	private GameObject m_result;

	private GameObject m_resultState1;

	private UILabel m_labelState1PreviousGuildName;

	private UILabel m_labelState1CurrentGuildName;

	private UITexture m_textureState1PreviousGuildEmblem;

	private UISprite m_textureState1PreviousGuildEmblemSprite;

	private UITexture m_textureState1CurrentGuildEmblem;

	private UISprite m_textureState1CurrentGuildEmblemSprite;

	private GameObject m_resultState2;

	private UILabel m_labelState2CurrentGuildName;

	private UITexture m_textureState2CurrentGuildEmblem;

	private UISprite m_textureState2CurrentGuildEmblemSprite;

	private GameObject m_resultState3;

	private static readonly int s_guildListCount;

	private ResultGuildList[] m_guilds;

	private UISprite m_spriteTownImage;

	private UILabelLocalized m_labelTownName;

	private NumberRotate m_scoreKill;

	private NumberRotate m_scoreKillPercent;

	private NumberRotate m_scoreContribution;

	private NumberRotate m_scoreContributionPercent;

	private UIGrid m_gridItem;

	private GameObject m_itemDim;

	private ulong m_myGuildId;

	private ulong m_myUserId;

	private void InitUI()
	{
	}

	private void DestroyUI()
	{
	}

	public void SetResult(ulong myGuildId, ulong myUserId, WorldData worldData)
	{
	}

	private void SetGuildMarkFrame(UISprite guildMarkFrame, int guildWarRank, bool isBig)
	{
	}

	private void UpdateGuildWarRewardUI(List<RewardInfo> items)
	{
	}

	private void UpdateUI()
	{
	}

	private void Start()
	{
	}

	public void OnInit(Transform parent)
	{
	}

	protected override void OnDestroy()
	{
	}

	private void Update()
	{
	}

	public override void ShowFinish()
	{
	}

	public void UpdatePanelUI()
	{
	}

	private void SetButtonsEnable(bool isEnable)
	{
	}

	public void OnClickBtnBattleHistory()
	{
	}

	public void GoToLobby()
	{
	}

	public void OnBtnClose()
	{
	}
}
