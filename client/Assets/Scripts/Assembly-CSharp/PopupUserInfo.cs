using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PopupUserInfo : CaocaoUIModalPopup
{
	public enum Tab
	{
		Default = 0,
		Nation = 1,
		BattleMap = 2,
		RecentCompetition = 3,
		Arena = 4
	}

	public enum ReopenStatus
	{
		None = 0,
		Replay = 1
	}

	public interface InnerUI
	{
		ulong uid { get; set; }

		void Init(Transform trs, ulong uid);

		void SetActive(bool active);

		void UpdateUI(JsonData response);

		void Destroy();
	}

	private static PopupUserInfo self;

	private static ReopenStatus reopenStatus;

	private static List<object> reopenArgs;

	private ulong uid;

	private UserDefaultInfoUI defaultUI;

	private UserNationInfoUI nationUI;

	private UserPvpMapInfoUI pvpMapUI;

	private UserReplayInfoUI replayUI;

	private UserArenaInfoUI arenaUI;

	private JsonData defaultData;

	private Tab curTab;

	private bool prevFollowStatus;

	private bool changedFollow;

	private string nationName;

	private CaocaoTabButtons m_tabButton;

	private GuildUIMarkName m_guildUI;

	private UILabelLocalized m_labelName;

	private UILabelLocalized m_labelRank;

	private UITexture m_texFace;

	private Transform m_objInteraction;

	private UIButton m_btnWatch;

	private UILabelLocalized m_labelRecommend;

	private UILabelLocalized m_labelFavorite;

	private GameObject m_objOnline;

	private UILabelLocalized m_labelStatus;

	private UIButton m_btnVisit;

	private UIButton m_btnFollow;

	private UIButton m_btnInvite;

	private GameObject m_objHeart;

	private UIButton m_btnOpen;

	private CaocaoTabButtons tabButton
	{
		get
		{
			return null;
		}
	}

	private GuildUIMarkName guildUI
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelName
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelRank
	{
		get
		{
			return null;
		}
	}

	private UITexture texFace
	{
		get
		{
			return null;
		}
	}

	private Transform objInteraction
	{
		get
		{
			return null;
		}
	}

	private UIButton btnWatch
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelRecommend
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelFavorite
	{
		get
		{
			return null;
		}
	}

	private GameObject objOnline
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelStatus
	{
		get
		{
			return null;
		}
	}

	private UIButton btnVisit
	{
		get
		{
			return null;
		}
	}

	private UIButton btnFollow
	{
		get
		{
			return null;
		}
	}

	private UIButton btnInvite
	{
		get
		{
			return null;
		}
	}

	private GameObject objHeart
	{
		get
		{
			return null;
		}
	}

	private UIButton btnOpen
	{
		get
		{
			return null;
		}
	}

	public static void Show(ulong uid, Tab tab = Tab.Default, Action validCallback = null, IDepthManagerblePopup parent = null)
	{
	}

	public static void Show(string nationName, Action validCallback = null)
	{
	}

	public static void CheckReopen()
	{
	}

	public static void Close()
	{
	}

	public static void SetReopenStatus(ReopenStatus status, params object[] args)
	{
	}

	public void OnInviteButton()
	{
	}

	private void Init()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	private void UpdateUI()
	{
	}

	private void Update()
	{
	}

	protected override void OnDestroy()
	{
	}

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
}
