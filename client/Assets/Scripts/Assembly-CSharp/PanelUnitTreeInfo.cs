using System;
using UnityEngine;

public class PanelUnitTreeInfo : CaocaoUIModalPopup
{
	public enum UnitInfo
	{
		Info = 0,
		Passive = 1,
		Training = 2,
		Rune = 3,
		Reinforce = 4,
		Promition = 5
	}

	private UserUnit m_userUnit;

	[SerializeField]
	private CaocaoTabButtons tabButtons;

	[SerializeField]
	private Transform m_trainingBtn;

	[SerializeField]
	private Transform m_runeBtn;

	[SerializeField]
	private Transform m_reinforceBtn;

	[SerializeField]
	private Transform m_promotionBtn;

	[SerializeField]
	private PanelUnitInfo m_infoTab;

	[ObjectWeakRef]
	public string m_trainingTabPath;

	[ObjectWeakRef]
	public string m_reinforceTabPath;

	[ObjectWeakRef]
	public string m_promotionTabPath;

	[SerializeField]
	private Transform m_arenaTileInfo;

	private PanelUnitTraining m_trainingTab;

	private PanelUnitReinforce m_reinforceTab;

	private PanelUnitPromotion m_promotionTab;

	private UnitInfo m_unitInfoMode;

	public PrefabInstance m_unitFavoriteInst;

	public GameObject m_closeBtn;

	[SerializeField]
	private GameObject m_fairRuleNoticeObj;

	[SerializeField]
	private UILabelLocalized m_fairRuleNoticeLabel;

	private bool m_init;

	private Action closeCallback;

	private bool m_started;

	private bool m_isPopup;

	public UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public PanelUnitInfo infoTab
	{
		get
		{
			return null;
		}
	}

	public PanelUnitTraining trainingTab
	{
		get
		{
			return null;
		}
	}

	public PanelUnitReinforce reinforceTab
	{
		get
		{
			return null;
		}
	}

	public PanelUnitPromotion promotionTab
	{
		get
		{
			return null;
		}
	}

	private GameObject m_prevBtn
	{
		get
		{
			return null;
		}
	}

	private GameObject m_nextBtn
	{
		get
		{
			return null;
		}
	}

	private GameObject m_favoriteBtn
	{
		get
		{
			return null;
		}
	}

	private GameObject m_unitListBtn
	{
		get
		{
			return null;
		}
	}

	protected override void OnDestroy()
	{
	}

	public static void ShowPopup(UserUnit userUnit, Action closeCallback = null, UnitInfo selecedTab = UnitInfo.Info)
	{
	}

	public static void ShowInfoPopup(int unitId, int level, int jobLevel)
	{
	}

	protected override void Awake()
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

	private void Start()
	{
	}

	public override void OnInit()
	{
	}

	private void InitUI()
	{
	}

	public void RefreshUI()
	{
	}

	public void ShowUnitTreeInfoPanel()
	{
	}

	public void ShowPanel(UserUnit userUnit, bool forcePopup = false)
	{
	}

	public void OnClosePanel()
	{
	}

	private void SelectTab(UnitInfo unitInfo)
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateFairRuleNotice()
	{
	}

	public void ShowHeroListPopup()
	{
	}

	public void ClickUnitFavorite()
	{
	}

	public void ShowPrevUnit()
	{
	}

	public void ShowNextUnit()
	{
	}

	private UserUnit GetPrevUnit()
	{
		return null;
	}

	private UserUnit GetNextUnit()
	{
		return null;
	}

	private bool IsProcessingReinforceOrPromotion()
	{
		return false;
	}
}
