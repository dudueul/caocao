using UnityEngine;

public class PopupGetUnitEffect : PanelSelfRegister2UIComponentsMonobehaviour
{
	[SerializeField]
	private GameObject[] m_passiveInfo;

	private UnitTreeNode m_unitTreeNode;

	private UserUnit m_userUnit;

	private bool m_byGold;

	private UserArenaUnit m_userArenaUnit;

	private UserArenaLevel m_userArenaLevel;

	private int m_promotionPresetId;

	private int m_arenaUnitJobLvIdx;

	public EventDelegate.Callback m_callback;

	private HeroInfoTag m_heroInfoTag
	{
		get
		{
			return null;
		}
	}

	private UnitDataForRenderer m_unitRenderer
	{
		get
		{
			return null;
		}
	}

	private GameObject m_ticketReward
	{
		get
		{
			return null;
		}
	}

	private PlayMakerFSM m_fsm
	{
		get
		{
			return null;
		}
	}

	private UIButton m_closeBtn
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_titleLabel
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_descLabel
	{
		get
		{
			return null;
		}
	}

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	public static void Show(ulong srl, int unitTreeNodeId, bool byGold, EventDelegate.Callback callback = null)
	{
	}

	public static void ShowArenaGetUnit(ulong srl, UserArenaLevel userArenaLevel, int promotionPresetId, EventDelegate.Callback callback = null)
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateSprite()
	{
	}

	private void UpdatePassive()
	{
	}

	private void UpdatePassiveDetail(GameObject panel, UnitPassive unitPassive, int index)
	{
	}

	private void UpdateTicketReward()
	{
	}
}
