using System.Collections.Generic;
using UnityEngine;

public class PanelPopupBoxShop : PanelPopupBaseShop
{
	private List<TreasureBoxItemInfo> treasureBoxItemInfos;

	private List<RuneBoxItemInfo> runeBoxItemInfos;

	private List<TreasureBoxItemInfo> promoteBoxItemInfos;

	private Transform m_infoTreasureBox;

	private UILabelLocalized m_ticketCount;

	private Transform m_infoRuneBox;

	protected static int storyStampTokenItemId;

	private Transform m_infoPromoteBox;

	private UILabelLocalized m_ticket1Count;

	private UILabelLocalized m_ticket2Count;

	private UITable m_infoButtonTable;

	private UIButton m_btnRuneDecompose;

	private UIButton m_btnRuneCompose;

	private int prevBoxTicket1;

	private int prevBoxTicket2;

	private int prevStoryStampCount;

	private int m_requestQueueCount;

	private Transform infoTreasureBox
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private UILabelLocalized ticketCount
	{
		get
		{
			return null;
		}
	}

	private Transform infoRuneBox
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private Transform infoPromoteBox
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private UILabelLocalized ticket1Count
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized ticket2Count
	{
		get
		{
			return null;
		}
	}

	private UITable infoButtonTable
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private UIButton btnRuneDecompose
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private UIButton btnRuneCompose
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void OnUpdateUserItems()
	{
	}

	private void UpdateBoxTicket()
	{
	}

	private void UpdateStoryStamp()
	{
	}

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void OnTabClicked(CaocaoButton button)
	{
	}

	private void OnTreasureBoxTabClicked()
	{
	}

	private void OnRuneBoxTabClicked()
	{
	}

	private void OnPromoteBoxTabClicked()
	{
	}

	public void OnRuneComposeBtnClicked()
	{
	}

	public void OnRuneDecomposeBtnClicked()
	{
	}

	private void Init()
	{
	}

	private void RequestForInitialize()
	{
	}

	private void OnRequestForInitialize()
	{
	}

	private void UpdateTab()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUITreasureBoxGrid()
	{
	}

	private void UpdateUIRuneBoxGrid()
	{
	}

	private void UpdateUIPromoteBoxGrid()
	{
	}

	private void UpdateTreasureBoxGrid()
	{
	}

	public static void Show()
	{
	}

	public static void Show(TabMenu tab)
	{
	}

	public static void RefreshBoxTicket()
	{
	}

	private string GetTabName()
	{
		return null;
	}
}
