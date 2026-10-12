using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class GuildBoardUIRead : MonoBehaviour
{
	public enum InputType
	{
		Text = 0,
		Emoticon = 1
	}

	private static GuildBoardUIRead self;

	private static int replyCountPerPage;

	[SerializeField]
	private UserCommonProfileSmall m_profile;

	[SerializeField]
	private UIScrollView m_scrollView;

	[SerializeField]
	private UITable m_table;

	[SerializeField]
	private UITable m_replayTable;

	[SerializeField]
	private Transform m_guildJobParent;

	[SerializeField]
	private UILabel m_nationName;

	[SerializeField]
	private UILabelLocalized m_regDate;

	[SerializeField]
	private UILabel m_content;

	[SerializeField]
	private GameObject m_normalObj;

	[SerializeField]
	private UILabelLocalized m_normalCategory;

	[SerializeField]
	private UILabel m_normalTitle;

	[SerializeField]
	private GameObject m_noticeObj;

	[SerializeField]
	private UILabelLocalized m_noticeCategory;

	[SerializeField]
	private UILabel m_noticeTitle;

	[SerializeField]
	private UILabelLocalized m_recommendCount;

	[SerializeField]
	private GameObject m_recommendedObj;

	[SerializeField]
	private UIInput m_replyInput;

	[SerializeField]
	private PopupEmoticon m_emoticonPopup;

	[SerializeField]
	private GameObject m_objEmoticonField;

	[SerializeField]
	private UIInput m_objTextField;

	[SerializeField]
	private UIGrid m_emoticonGrid;

	private GuildBoardReplyCountTableItem m_replyCount;

	private GuildBoardReplyPageTableItem m_replyPage;

	private Dictionary<ulong, GuildBoardReplyData> m_replyDatas;

	private Dictionary<ulong, GuildBoardReplyListItem> m_replyListItems;

	private bool m_didRecommend;

	private int m_currentPage;

	private GuildBoardPostData m_postData;

	private InputType m_inputType;

	private List<ChatStickerListItem> m_chatStickerItems;

	public void Show(GuildBoardPostData postData)
	{
	}

	public void Close()
	{
	}

	private void Init()
	{
	}

	private void ResetTableItem()
	{
	}

	private void UpdateUI(List<GuildBoardReplyData> replies)
	{
	}

	private void UpdatePage()
	{
	}

	private void UpdatePostDataUI()
	{
	}

	private void OnPageSelected(int page)
	{
	}

	private void OnUpdateUserInfo(UserInfo userInfo)
	{
	}

	private void UpdateRecommendBtn()
	{
	}

	public void OnClickReply()
	{
	}

	private void SendReply(string content, Action sendCallback)
	{
	}

	private void OnWriteReply(JsonData response)
	{
	}

	private bool IsValid(UIInput input)
	{
		return false;
	}

	public void OnClickRecommend()
	{
	}

	private void OnRecommend(JsonData response)
	{
	}

	private void RequestMyRecommend()
	{
	}

	private void OnGetRecommend(JsonData response)
	{
	}

	private void RequestReply(int page)
	{
	}

	private void OnGetReply(JsonData response)
	{
	}

	public static void RefreshReply()
	{
	}

	public void SetInputType(InputType inputType)
	{
	}

	public void AddEmoticon(int stickerId)
	{
	}

	public void OnPopupEmoticonClose()
	{
	}

	private void OnRemoveEmotionClick(int stickerId)
	{
	}

	private void ClearStickerItems()
	{
	}

	public void OnBtnEmoticon()
	{
	}

	public void OnBtnSend()
	{
	}
}
