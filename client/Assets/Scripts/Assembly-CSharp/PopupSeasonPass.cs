using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PopupSeasonPass : CaocaoUIModalPopup
{
	private SeasonPass pdSeasonPass;

	private List<SeasonPassReward> lstNormalRewards;

	private List<SeasonPassReward> lstEliteRewards;

	private int prevNormalLevel;

	private int prevEliteLevel;

	private Action closeCallback;

	private bool passAbled;

	private UILabelLocalized m_labelTitle;

	private UILabelLocalized m_labelMonth;

	private UILabelLocalized m_labelPeriod;

	private UILabelLocalized m_labelLevel;

	private UISlider m_expGauge;

	private UILabelLocalized m_labelDesc;

	private UILabelLocalized m_labelExp;

	private UILabelLocalized m_labelNormalSoldier;

	private UILabelLocalized m_labelEliteSolider;

	private GameObject m_btnPurchase;

	private GameObject m_objPurchaseDesc;

	private UIGrid m_grid;

	private UIScrollBar m_scrollBar;

	private UIButton m_btnPass;

	private UILabelLocalized labelTitle
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelMonth
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelPeriod
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelLevel
	{
		get
		{
			return null;
		}
	}

	private UISlider expGauge
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelDesc
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelExp
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelNormalSoldier
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelEliteSolider
	{
		get
		{
			return null;
		}
	}

	private GameObject btnPurchase
	{
		get
		{
			return null;
		}
	}

	private GameObject objPurchaseDesc
	{
		get
		{
			return null;
		}
	}

	private UIGrid grid
	{
		get
		{
			return null;
		}
	}

	private UIScrollBar scrollBar
	{
		get
		{
			return null;
		}
	}

	private UIButton btnPass
	{
		get
		{
			return null;
		}
	}

	protected override void Awake()
	{
	}

	public static void Show(SeasonPass pdSeasonPass, List<SeasonPassReward> lstNormalRewards, List<SeasonPassReward> lstEliteRewards, Action closeCallback)
	{
	}

	public void OnPassButton()
	{
	}

	public void OnMissionButton()
	{
	}

	private void OnGetRewardResponse(JsonData response)
	{
	}

	public void OnPurchaseButton()
	{
	}

	private void OnDeserializeCashShopInfo()
	{
	}

	private void InitUI()
	{
	}

	private void OnReceivedReward(JsonData packet)
	{
	}

	public void UpdateUI()
	{
	}

	protected override void OnDestroy()
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
