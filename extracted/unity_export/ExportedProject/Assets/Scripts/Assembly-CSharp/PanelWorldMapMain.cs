using System.Collections.Generic;
using UnityEngine;

public class PanelWorldMapMain : MonoBehaviour
{
	private static PanelWorldMapMain self;

	public GameObject m_mainButtons;

	public GameObject m_leftButtons;

	public GameObject m_rightButtons;

	public UIButton m_btn1;

	public UIButton m_btn5;

	public UIButton m_btn6;

	public UIButton m_btnCityList;

	public UIButton m_btnBattle;

	public UIButton m_btnSwitchLobbyAndMap;

	public UIButton m_btnNationPopularity;

	public UIButton m_btnGuild;

	public UIButton m_btnTreasureCraft;

	public UILabelLocalized m_backButtonLabel;

	public DailyGiftButton m_btnDailyGift;

	public InstanceButton m_instanceButton;

	private PanelTownList m_panelTownList;

	public GameObject m_historyNew;

	private List<GameObject> m_buttons;

	private string m_lastRewardRequestTime;

	private GameObject m_exMarkMap;

	private GameObject m_exMarkLobby;

	private bool checkJointGuild;

	public PanelTownList panelTownList
	{
		get
		{
			return null;
		}
	}

	private string eventRankRewardKey
	{
		get
		{
			return null;
		}
	}

	private string lastRewardRequestTime
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private GameObject exMarkMap
	{
		get
		{
			return null;
		}
	}

	private GameObject exMarkLobby
	{
		get
		{
			return null;
		}
	}

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}

	private void ToggleAllUI(bool active)
	{
	}

	public static void Toggle(bool active)
	{
	}

	public static void ToggleAllButton(bool active)
	{
	}

	public static void ToggleWorldmapUI(bool active)
	{
	}

	public static void SetActive(bool active)
	{
	}

	public static bool GetActive()
	{
		return false;
	}

	private void ShowButtons(bool active)
	{
	}

	private void ShowAllButtons(bool active)
	{
	}

	private void ShowWorldmapButtons(bool active)
	{
	}

	public void ShowTownList()
	{
	}

	public void ShowShamBattlePanel()
	{
	}

	private void ShowPackManIcon()
	{
	}

	private void ShowPackManHelpIcon()
	{
	}

	private void UpdateAlarms()
	{
	}

	public void UpdateBattleAlarm()
	{
	}

	private void UpdateShopAlarm()
	{
	}

	public static void UpdateManagementAlarm()
	{
	}

	public void ClickMainMenu()
	{
	}

	private void OnUpdateMyGuild()
	{
	}

	private void OpenGuildMain()
	{
	}

	private void OnErrorUpdateMyGuild(string error, ref bool handled)
	{
	}

	public static void UpdateTitleLabel(bool isLobbyOn)
	{
	}

	public void ClickTreasureBook()
	{
	}

	public void ClickSetting()
	{
	}

	public void ClickRanking()
	{
	}

	public void ClickHistory()
	{
	}

	public void ClickNation()
	{
	}

	public void ClickSoldierTraining()
	{
	}

	public void SetVisibleMainButtons(bool flag)
	{
	}

	public void SetVisibleAllUI(bool flag)
	{
	}

	private void DeactivateGOForFullScreenPanelUI()
	{
	}

	private void ActivateGOForFullScreenPanelUI()
	{
	}

	public void UpdateHistoryNew()
	{
	}

	public static void UpdatePopularityNewTag()
	{
	}

	public static void CheckInstanceButtonLimitTag()
	{
	}

	public static void UpdateInstanceBtn()
	{
	}

	public static void UpdateShopAlram()
	{
	}

	public void TimerJob()
	{
	}

	private void CheckRaidAwakenInfo()
	{
	}

	private void CheckWorldbossInfo()
	{
	}

	public void CheckCustomEvent()
	{
	}

	public void RequestEventRankReward(bool force)
	{
	}
}
