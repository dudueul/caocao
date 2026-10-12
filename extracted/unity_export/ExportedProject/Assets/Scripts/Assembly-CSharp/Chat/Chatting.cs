using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using SocketIO;

namespace Chat
{
	public class Chatting : Singleton<Chatting>
	{
		public Dictionary<string, EnumData.Locale> Languages;

		private static readonly string ms_chatRoomLocalePrefsKey;

		private SocketIOComponent m_socket;

		private const int GetAuthKeyTimeTickSecs = 3600;

		private const int WhisperTimeOut = 2;

		private ServerInfo m_currentServerInfo;

		private List<Room> m_rooms;

		private Room m_multiplayWatchRoom;

		private ObscuredString m_authKey;

		private ChattingUser m_chattingUserMe;

		private List<ChatBlockUser> m_chatBlockUserList;

		private HashSet<string> m_friendUuids;

		private Dictionary<string, ChattingUser> m_chattingUsers;

		private Dictionary<string, HashSet<string>> m_channel2uuids;

		private Dictionary<string, List<EventArgs>> m_chattingMessages;

		private List<Notification> m_notifications;

		private bool m_userRegistered;

		private int m_messageIndex;

		private const string m_allMsgRoomId = "all";

		private List<double> m_normalChatCoolTimes;

		private List<int> m_whisperResponseMsgIndex;

		private EventHandler OnConnected__BackingField;

		private EventHandler OnDisconnected__BackingField;

		private EventHandler OnUserRegistered__BackingField;

		private EventHandler OnNotFoundRoomIdReceived__BackingField;

		private EventHandler OnSocketError__BackingField;

		private EventHandler<ChattingMessage> OnChattingMessageReceived__BackingField;

		private EventHandler<ChattingUser> OnChattingUserChanged__BackingField;

		private EventHandler<WhisperResponse> OnWhisperResponded__BackingField;

		private EventHandler OnWhisperNotFoundResponded__BackingField;

		private EventHandler<Notification> OnNotificationReceived__BackingField;

		private EventHandler<CustomMessage> OnCustomMessageReceived__BackingField;

		private EventHandler<ChattingUserJoinToChannel> OnChattingUserJoinToChannel__BackingField;

		private EventHandler<ChattingUserLeaveFromChannel> OnChattingUserLeaveFromChannel__BackingField;

		public EChatRoomLocale GetChatRoomLocale
		{
			get
			{
				return EChatRoomLocale.Base;
			}
		}

		public List<Room> Rooms
		{
			get
			{
				return null;
			}
		}

		public Room multiplayWatchRoom
		{
			get
			{
				return null;
			}
		}

		public ChattingUser UserMe
		{
			get
			{
				return null;
			}
		}

		public int NextMessageIndex
		{
			get
			{
				return 0;
			}
		}

		public IEnumerable<ChattingUser> ChattingUsers
		{
			get
			{
				return null;
			}
		}

		public IEnumerable<Notification> Notifications
		{
			get
			{
				return null;
			}
		}

		public bool IsConnected
		{
			get
			{
				return false;
			}
		}

		public bool isChatEncabled
		{
			get
			{
				return false;
			}
		}

		public bool IsConnecting
		{
			get
			{
				return false;
			}
		}

		public ChattingUserState MyState
		{
			set
			{
			}
		}

		public bool UserRegistered
		{
			get
			{
				return false;
			}
		}

		public string GetBaseNormalRoomId
		{
			get
			{
				return null;
			}
		}

		public event EventHandler OnConnected
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler OnDisconnected
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler OnUserRegistered
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler OnNotFoundRoomIdReceived
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler OnSocketError
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler<ChattingMessage> OnChattingMessageReceived
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler<ChattingUser> OnChattingUserChanged
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler<WhisperResponse> OnWhisperResponded
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler OnWhisperNotFoundResponded
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler<Notification> OnNotificationReceived
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler<CustomMessage> OnCustomMessageReceived
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler<ChattingUserJoinToChannel> OnChattingUserJoinToChannel
		{
			add
			{
			}
			remove
			{
			}
		}

		public event EventHandler<ChattingUserLeaveFromChannel> OnChattingUserLeaveFromChannel
		{
			add
			{
			}
			remove
			{
			}
		}

		public EnumData.Locale getLanguage(EChatRoomLocale roomLocale)
		{
			return EnumData.Locale.NONE;
		}

		public EChatRoomLocale getRoomLocale(EnumData.Locale language)
		{
			return EChatRoomLocale.Base;
		}

		public string getLocaleName()
		{
			return null;
		}

		public void SetChatRoomLocale(EChatRoomLocale roomLocale)
		{
		}

		public static T ToObjectForGenericReflection<T>(string json)
		{
			return default(T);
		}

		protected override void Awake()
		{
		}

		protected override void OnDestroy()
		{
		}

		public void ConnectSocket(string url, string authKey)
		{
		}

		public void RegisterChattingUser(string uuid, string userName)
		{
		}

		public void RegisterFriends(string myUserName, List<ChattingUser> friendChattingUsers)
		{
		}

		public void ChannelListRecieved(JsonData roomListData)
		{
		}

		public void JoinToChannel(string channelId)
		{
		}

		public void LeaveFromChannel(string channelId)
		{
		}

		public void ChatToAll(string message, ChattingMessage.ChattingMessageType chatType, ChattingMessage.ChattingMessageInputType chatInputType)
		{
		}

		public void ChatToChannel(string toChannelId, string message, ChattingMessage.ChattingMessageType chatType = ChattingMessage.ChattingMessageType.Channel, ChattingMessage.ChattingMessageInputType chatInputType = ChattingMessage.ChattingMessageInputType.Text)
		{
		}

		public void ChatToUser(string toUserName, string message, ChattingMessage.ChattingMessageInputType chatInputType)
		{
		}

		[DebuggerHidden]
		private IEnumerator WhisperResponseTimeout(string toUserName, int messageIndex)
		{
			return null;
		}

		public void NotifyTo(string toUserName, object notificationValue)
		{
		}

		public void CustomMessageTo(string toChannelId, object customMessageObject)
		{
		}

		public void CustomMessageToUser(string toUserName, object customMessageObject)
		{
		}

		public ChattingUser GetChattingUser(string uuid)
		{
			return null;
		}

		public void RemoveNotification(Notification notification)
		{
		}

		public void ChangeUserName(string newUserName)
		{
		}

		private void EmitRegisterUser(string uuid, string userName, string authKey)
		{
		}

		private void EmitRegisterSubscribingRooms(List<string> roomIds)
		{
		}

		private void EmitJoinRoom(string roomId)
		{
		}

		private void EmitLeaveRoom(string roomId)
		{
		}

		private void EmitBroadcast(BroadcastType type, object obj, string toRoomId)
		{
		}

		private void EmitChattingMessage(ChattingMessage chattingMessage)
		{
		}

		private void EmitChattingUserMeToJoinedChannelsAndPub()
		{
		}

		private void EmitChattingUser(ChattingUser user, string toRoomId)
		{
		}

		private void EmitChattingUserRequest(ChattingUserRequest req, string toRoomId)
		{
		}

		private void EmitWhisperResponse(WhisperResponse res, string toRoomId)
		{
		}

		private void EmitNotification(TypeValuePair pair, string toUserName)
		{
		}

		private void EmitCustomMessage(TypeValuePair pair, string toChannelId)
		{
		}

		private void OnConnect(SocketIOEvent e)
		{
		}

		private void OnDisconnect(SocketIOEvent e)
		{
		}

		private void OnRegistered(SocketIOEvent e)
		{
		}

		private void OnBroadcastTo(SocketIOEvent e)
		{
		}

		private void OnNotFoundRoomId(SocketIOEvent e)
		{
		}

		private void OnBroadcastDisconnectingTo(SocketIOEvent e)
		{
		}

		private void OnError(SocketIOEvent e)
		{
		}

		private void OnCheckBanContent(SocketIOEvent e)
		{
		}

		private void OnChattingUser(ChattingUser user)
		{
		}

		private void OnChattingMessage(ChattingMessage msg)
		{
		}

		private void OnChattingUserRequest(ChattingUserRequest req)
		{
		}

		private void OnWhisperResponse(WhisperResponse res)
		{
		}

		private void OnNotification(Notification noti)
		{
		}

		private void OnCustomMessage(CustomMessage customMsg)
		{
		}

		public string GetSubRoomId(string id)
		{
			return null;
		}

		public string GetPubRoomId(string id)
		{
			return null;
		}

		public string GetGuildRoomId(ulong id)
		{
			return null;
		}

		public string GetChannelRoomId(List<Room> roomList)
		{
			return null;
		}

		private string GetDestRoomId(ChattingMessage.ChattingMessageType chatType)
		{
			return null;
		}

		private string GetDestRoomId(EventArgs msg)
		{
			return null;
		}

		public void GetEmptyChannel()
		{
		}

		private BroadcastType GetBroadcastType(ChattingMessage.ChattingMessageType type)
		{
			return BroadcastType.User;
		}

		private void CleanJoinedChannelUUIDs(ChattingUser user)
		{
		}

		private void CleanChattingUser(string uuid)
		{
		}

		private void CleanChattingUser(ChattingUser user)
		{
		}

		private bool IsThereAnySameChannel(ChattingUser userA, ChattingUser baseUser)
		{
			return false;
		}

		public List<EventArgs> GetChatMsgs(string roomId)
		{
			return null;
		}

		private void AddChatMsg(EventArgs msg)
		{
		}

		private void AddChat(string roomId, EventArgs msg)
		{
		}

		private void TrimChat(string roomId)
		{
		}

		private void ClearChatMsg(ChattingMessage.ChattingMessageType msgType)
		{
		}

		public List<ChatBlockUser> GetBlockList()
		{
			return null;
		}

		public bool IsBlockUser(ulong userId)
		{
			return false;
		}

		public void AddBlockUser(ChatBlockUser blockUser)
		{
		}

		public void RemoveBlockUser(ulong userId)
		{
		}

		public void DeserializeBlockUsers(JsonData jsonData)
		{
		}

		public List<ChattingMessage> GetChatBlockMsgList(int pivotMsgIndex)
		{
			return null;
		}

		private void CleanCoolTime(double now)
		{
		}

		private int GetFirstCoolTime(ChattingMessage.ChattingMessageType chatType, double now)
		{
			return 0;
		}

		private int GetAvaliableChatMsgCount(ChattingMessage.ChattingMessageType chatType)
		{
			return 0;
		}

		private void AddCoolTime(ChattingMessage.ChattingMessageType chatType, double now)
		{
		}

		private void SendGetChatAuthKey()
		{
		}

		public void GetServerInfo()
		{
		}

		public void GetChatAuthKey()
		{
		}

		public void GetRoomList()
		{
		}

		private void GetGuildSaveMessage()
		{
		}

		public void GetBlockListFromServer()
		{
		}

		public void OnChatRoomListRecieved(JsonData response)
		{
		}

		public void OnChatServerInfoRecieved(JsonData response)
		{
		}

		public void OnChatAuthKeyRecieved(JsonData response)
		{
		}

		public void OnChatGuildSaveMessageRecieved(JsonData response)
		{
		}

		public void OnChatBlockListRecieved(JsonData response)
		{
		}

		private void AddGuildInfoChangeEventHandler()
		{
		}

		private void RemoveGuildInfoChangeEventHandler()
		{
		}

		private void OnGuildJoin(JsonData response)
		{
		}

		private void OnGuildGet(JsonData response)
		{
		}

		private void OnGuildLeave(JsonData response)
		{
		}

		private void OnGuildError(string error, ref bool handled)
		{
		}

		public void JoinToMultiplayWatchChannel(string channelId)
		{
		}

		public void LeaveFromMultiplayWatchChannel()
		{
		}
	}
}
