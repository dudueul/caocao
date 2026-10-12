using LitJson;
using UnityEngine;

public class PopupGuildBoardWrite : CaocaoUIModalPopup
{
	private enum Mode
	{
		Write = 0,
		Modify = 1
	}

	private static PopupGuildBoardWrite self;

	[SerializeField]
	private UILabelLocalized m_windowLabel;

	[SerializeField]
	private GameObject m_filterObj;

	[SerializeField]
	private UILabelLocalized m_filterLabel;

	[SerializeField]
	private UIInput m_titleInput;

	[SerializeField]
	private UIInput m_contentInput;

	[SerializeField]
	private UILabelLocalized m_titleLabel;

	[SerializeField]
	private UILabelLocalized m_contentLabel;

	private Mode m_mode;

	private GuildBoardPostData m_postData;

	private GuildBoardPostData.Type m_currentType;

	public static void Show(GuildBoardPostData postData, IDepthManagerblePopup parentPopup)
	{
	}

	private void Init()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateTypeUI()
	{
	}

	private bool IsValid(UIInput input)
	{
		return false;
	}

	public void OnClickFilter()
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

	public void ChangeFilter(GuildBoardPostData.Type type)
	{
	}

	public void OnClickComplete()
	{
	}

	private void RequestWrite()
	{
	}

	private void OnWrite(JsonData response)
	{
	}

	private void RequestModify()
	{
	}

	private void OnModify(JsonData response)
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
