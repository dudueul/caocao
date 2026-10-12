using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PanelGuildBoardList : CaocaoUIModalPopup
{
	[SerializeField]
	private GameObject m_listObj;

	[SerializeField]
	private GuildBoardUIRead m_readUI;

	[SerializeField]
	private GameObject m_filterObj;

	[SerializeField]
	private UILabelLocalized m_filterLabel;

	[SerializeField]
	private UIReuseScrollView m_scrollView;

	[SerializeField]
	private UIReuseGrid m_grid;

	[SerializeField]
	private GameObject m_optionWindow;

	[SerializeField]
	private Transform m_optionTransform;

	[SerializeField]
	private UILabel m_optionTitle;

	[SerializeField]
	private UILabel m_optionName;

	[SerializeField]
	private UIButton m_setNoticeBtn;

	[SerializeField]
	private UILabelLocalized m_setNoticeLabel;

	[SerializeField]
	private UIButton m_deleteBtn;

	[SerializeField]
	private UIButton m_modifyBtn;

	private List<ulong> m_noticeSrls;

	private Dictionary<ulong, GuildBoardPostData> m_postDatas;

	private GuildBoardPostData m_currentPost;

	private GuildBoardListItem m_currentItem;

	private int m_page;

	private GuildBoardPostData.Type m_currentFilter;

	private bool m_fromChat;

	public static PanelGuildBoardList self { get; private set; }

	public static bool isAlive
	{
		get
		{
			return false;
		}
	}

	public GuildBoardPostData.Type currentFilter
	{
		get
		{
			return GuildBoardPostData.Type.All;
		}
		set
		{
		}
	}

	public static void Show(bool fromChat, IDepthManagerblePopup parent = null)
	{
	}

	public static void Close()
	{
	}

	public static void Read(GuildBoardListItem listItem)
	{
	}

	public static void ShowOption(GuildBoardListItem listItem)
	{
	}

	public static void Refresh()
	{
	}

	public static void ShowList()
	{
	}

	public static void OnCurrentPostModified()
	{
	}

	public static DepthManager.Grade GetDepth()
	{
		return (DepthManager.Grade)0;
	}

	private void Start()
	{
	}

	private void Init()
	{
	}

	private void ResetGridItem(bool update = false)
	{
	}

	private void UpdateUI(List<GuildBoardPostData> addPosts)
	{
	}

	public void OnClickShowFilter()
	{
	}

	public void OnClickFilterAll()
	{
	}

	public void OnClickFilterChat()
	{
	}

	public void OnClickFilterInfo()
	{
	}

	public void OnClickFilterGuildWar()
	{
	}

	private void ChangeFilter(GuildBoardPostData.Type type)
	{
	}

	private void NextPageLoading()
	{
	}

	private void RequestBoard()
	{
	}

	private void OnGetBoard(JsonData response)
	{
	}

	public void ReadPost(GuildBoardListItem listItem)
	{
	}

	public void OnClickWrite()
	{
	}

	public void ShowPostOption(GuildBoardListItem listItem)
	{
	}

	public void OnClickSetNotice()
	{
	}

	private void OnSetNotice(JsonData response)
	{
	}

	public void OnClickDelete()
	{
	}

	private void OnDelete(JsonData response)
	{
	}

	public void OnClickModify()
	{
	}

	private void UpdateCurrentItem()
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
