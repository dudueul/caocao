using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using UnityEngine;

public class PanelInstanceLobby : PanelSelfRegister2UIComponentsMonobehaviour
{
	public static PanelInstanceLobby self;

	private EnumData.EventType m_currentMode;

	private EnumData.EventType m_prevMode;

	private bool m_initialized;

	private bool m_sceneDataPipeChecked;

	private CaocaoTabButtons m_tabs;

	private GameObject m_specialTabButtonNotify;

	private GameObject m_limitTabButton;

	private GameObject m_limitTabButtonNotify;

	private InstanceTabNormal m_tabNormal;

	private InstanceTabSpecial m_tabSpecial;

	private InstanceTabLimited m_tabLimit;

	private UIButton m_upleftBtn;

	private UIButton m_bottomStoreBtn;

	private Transform m_bottomStoreNewtag;

	private Transform m_bottomStoreLimit;

	private UIButton m_bottomManagementBtn;

	private Transform m_bottomManagementNewtag;

	private PackManIconUI m_bottomPackManStoreBtn;

	private PackManHelpIconUI m_bottomPackManHelpBtn;

	private Transform m_notice;

	private Transform m_noticeRemainTime;

	private GameObject m_stateIconTemplate;

	private Transform m_bottomButtonContainer;

	private Transform m_stateMoveButtonContainer;

	private Transform m_background;

	private InstanceInformation m_informationPanel;

	private InstanceStateList m_eventList;

	private UIButton m_stateMoveLeftButton;

	private UIButton m_stateMoveRightButton;

	private UILabelLocalized m_stateMoveLeftName;

	private UILabelLocalized m_stateMoveRightName;

	private UIButton m_bottomSequenceButton;

	private UIButton m_bottomExpeditionButton;

	private UILabelLocalized m_remainExpeditionTime;

	private Transform m_expeditionNewtag;

	[HideInInspector]
	public GameObject StateIconTemplate
	{
		get
		{
			return null;
		}
	}

	[HideInInspector]
	public Transform BottomButtonContainer
	{
		get
		{
			return null;
		}
	}

	[HideInInspector]
	public Transform StateMoveButtonContainer
	{
		get
		{
			return null;
		}
	}

	[HideInInspector]
	public Transform Background
	{
		get
		{
			return null;
		}
	}

	[HideInInspector]
	public InstanceInformation InformationPanel
	{
		get
		{
			return null;
		}
	}

	[HideInInspector]
	public InstanceStateList EventList
	{
		get
		{
			return null;
		}
	}

	[HideInInspector]
	public UIButton StateMoveLeftButton
	{
		get
		{
			return null;
		}
	}

	[HideInInspector]
	public UIButton StateMoveRightButton
	{
		get
		{
			return null;
		}
	}

	[HideInInspector]
	public UILabelLocalized StateMoveLeftName
	{
		get
		{
			return null;
		}
	}

	[HideInInspector]
	public UILabelLocalized StateMoveRightName
	{
		get
		{
			return null;
		}
	}

	[HideInInspector]
	public UIButton BottomSequenceButton
	{
		get
		{
			return null;
		}
	}

	[HideInInspector]
	public UIButton BottomExpeditionButton
	{
		get
		{
			return null;
		}
	}

	[HideInInspector]
	public UILabelLocalized RemainExpeditionTime
	{
		get
		{
			return null;
		}
	}

	[HideInInspector]
	public Transform ExpeditionNewtag
	{
		get
		{
			return null;
		}
	}

	private void Start()
	{
	}

	protected void Initialize()
	{
	}

	public void CheckShowPackManStoreIcon()
	{
	}

	public void HidePackManHelpIcon()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void CheckShopAlarms()
	{
	}

	private void CheckManagementAlarm()
	{
	}

	private void CheckBonusSubscription()
	{
	}

	private void UpdateSubscriptionTimer()
	{
	}

	private void UpdateExpeditionTimer()
	{
	}

	public void SetBackBtnTilte(string title)
	{
	}

	public void UpdateUIExpedition()
	{
	}

	private void BottomStoreButtonClicked()
	{
	}

	private void BottomManagementButtonClicked()
	{
	}

	public void UpdateUI()
	{
	}

	public void ChangeTab(EnumData.EventType mode)
	{
	}

	public void OnTabChanged(CaocaoButton button)
	{
	}

	private void OnModeChanged(EnumData.EventType mode)
	{
	}

	private void BackButtonClciked()
	{
	}

	public void ForceSelectInstance()
	{
	}

	public static void Show()
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	public void CheckInstance()
	{
	}

	public void CheckInstanceSpecificInstances(List<ObscuredInt> instIds)
	{
	}

	private bool CheckTabStatus(EnumData.EventType eventType, bool alertDisabled)
	{
		return false;
	}

	public void OnCheckInstanceOnInit(JsonData response)
	{
	}

	public void GoToWorldMap()
	{
	}

	public void GoToNormalInstance(int instanceId)
	{
	}

	private void OnCompleteLoading()
	{
	}
}
