using System;
using System.Collections.Generic;
using Chat;
using LitJson;
using UnityEngine;

public class PanelChat : CaocaoUIModalPopup
{
	private enum ChatType
	{
		Channel = 0,
		All = 1,
		Whisper = 2,
		Guild = 3,
		Max = 4
	}

	public enum ChatInputType
	{
		Text = 0,
		Emoticon = 1
	}

	public Color m_chatAllColor;

	public Color m_chatNormalColor;

	public Color m_chatNoticeColor;

	public Color m_chatWhisperColor;

	public Color m_chatGuildColor;

	public Color m_chatSystemColor;

	private static ChatType m_chatType;

	private static ChatType m_tabType;

	private static EChatRoomLocale m_RoomLocale;

	private static string m_whisperToName;

	private string m_selectedChannelId;

	private static ChatInputType m_chatInputType;

	private List<ChatStickerListItem> m_chatStickerItems;

	[SerializeField]
	private GameObject m_objChatOption;

	[SerializeField]
	private ToggleButtonUI m_toggleChatOneLine;

	[SerializeField]
	private ToggleButtonUI m_toggleChatEmoticon;

	[SerializeField]
	private GameObject m_objChatTypeList;

	[SerializeField]
	private GameObject[] m_objChatTypes;

	[SerializeField]
	private UIInput[] m_inputs;

	[SerializeField]
	private UIInput m_inputUserName;

	[SerializeField]
	private UIInput[] m_inputLocale;

	[SerializeField]
	private GameObject m_objSend;

	[SerializeField]
	private GameObject m_objSendGold;

	[SerializeField]
	private UILabelLocalized m_labelSendGoldValue;

	[SerializeField]
	private UILabelLocalized m_channelName;

	[SerializeField]
	private GameObject m_objChannelList;

	[SerializeField]
	private UIReuseGrid m_gridChannelList;

	[SerializeField]
	private UIReuseScrollView m_scrollViewChannelList;

	[SerializeField]
	private UIReuseScrollView m_reuseScrollView;

	[SerializeField]
	private UIReuseGrid m_reuseGrid;

	[SerializeField]
	private CaocaoTabButtons m_tabButtons;

	[SerializeField]
	private PopupWhisper m_popupWhisper;

	[SerializeField]
	private UIButton m_blockListBtn;

	[SerializeField]
	private PopupEmoticon m_objEmoticonPopup;

	[SerializeField]
	private GameObject m_objEmoticonNormalField;

	[SerializeField]
	private UIGrid m_EmoticonNormalGrid;

	[SerializeField]
	private GameObject m_objEmoticonWhisperField;

	[SerializeField]
	private UIGrid m_EmoticonWhisperGrid;

	[SerializeField]
	private GameObject m_objEmoticonSend;

	[SerializeField]
	private GameObject m_objEmoticonSendGold;

	private bool m_isOpenGoldPopup;

	public static Color ChatAllColor;

	public static Color ChatChannelColor;

	public static Color ChatNoticeColor;

	public static Color ChatWhisperColor;

	public static Color ChatGuildColor;

	private void InitUI()
	{
	}

	private void InitTabBtn()
	{
	}

	private string GetTabName()
	{
		return null;
	}

	private void UpdateChannelId()
	{
	}

	private void UpdateChatUI(ChatType type)
	{
	}

	private void UpdateBlockBtnState()
	{
	}

	public void OnBtnGlobalChannel()
	{
	}

	public void OnBtnChangeRoomLocale(EChatRoomLocale type)
	{
	}

	public void OnBtnSend()
	{
	}

	public void OnBtnSendGold()
	{
	}

	public void OnBtnChatTab(CaocaoButton button)
	{
	}

	public void OnBtnChatType()
	{
	}

	public void OnBtnChannelList(string channelName)
	{
	}

	public void OnToggleChatOneLine(bool enable)
	{
	}

	public void OnToggleChatEmoticon(bool enable)
	{
	}

	public void OnBtnBlockList()
	{
	}

	public void OnBtnChatOption()
	{
	}

	public void OnBtnEmoticon()
	{
	}

	private void SetChatType(ChatType type)
	{
	}

	private void EnableChatTypeObj(ChatType type)
	{
	}

	public void SetChatInputType(ChatInputType chatInputType)
	{
	}

	private void EnableChatInputObj(ChatType type)
	{
	}

	private void UpdateSendGoldUI()
	{
	}

	private bool IsValidComment(UIInput input)
	{
		return false;
	}

	private bool CheckSendGoldLimit()
	{
		return false;
	}

	private void NotFonudWhisperUser()
	{
	}

	private void InitChatColor()
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

	private void OnGoldChatResponse(JsonData response)
	{
	}

	private void OnGetRooms(bool getRoomId)
	{
	}

	public void OnChatRoomListRecieved(JsonData response)
	{
	}

	public void AddChatMessage(object sender, EventArgs e)
	{
	}

	public void AddNotification(object sender, EventArgs e)
	{
	}

	private void AutoNextLineChat(EventArgs eArgs, string msg = "", bool setOnClickCallback = false)
	{
	}

	private void AddNextLineSplit(EventArgs eArgs, string splitMsg, bool setOnClickCallback)
	{
	}

	public void AddWhisperResponded(object sender, EventArgs e)
	{
	}

	public void AddJoinToChannel(object sender, EventArgs e)
	{
	}

	public void OnChatItemClick(string toName, string toUserId, int msgIndex)
	{
	}

	public void OnWhisperChatClick(string toName)
	{
	}

	public void OnUserBlockSuccess()
	{
	}

	private void AddEventHandler()
	{
	}

	private void RemoveEventHandler()
	{
	}

	protected override void Awake()
	{
	}

	private void OnWatchMultiplayCallback()
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
