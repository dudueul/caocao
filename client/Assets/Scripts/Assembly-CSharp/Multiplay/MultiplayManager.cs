using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using BattlePlay;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using SocketIO;
using UnityEngine;

namespace Multiplay
{
	public class MultiplayManager : Singleton<MultiplayManager>
	{
		public enum EventType
		{
			Connected = 0,
			Disconnected = 1,
			SocketError = 2,
			UserRegistered = 3
		}

		public class ServerInfo
		{
			public ObscuredString ip;

			public ObscuredInt port;

			public ObscuredInt cnt;

			public string Uri
			{
				get
				{
					// [RE] ws://{ip}/socket.io/?EIO=4&transport=websocket (template
					// literal); the rebuilt server's getServerInfo already returns
					// the full ws URL in `ip`.
					return string.Format("ws://{0}/socket.io/?EIO=4&transport=websocket", (string)ip);
				}
			}
		}

		private class DeniedInvitingInfo
		{
			public Dictionary<ulong, DateTime> deniedTimes;

			public bool CheckDeniedInvitingTime(ulong targetId)
			{
				return false;
			}

			public DateTime GetDeniedInvitingTime(ulong targetId)
			{
				return default(DateTime);
			}

			public void StoreDeniedInfo(ulong targetId)
			{
			}
		}

		private static int devVersion;

		public NetworkReachability testNetworkReachability;

		public NetworkReachability lastNetworkReachability;

		private float lastNetworkReachabilityCheck;

		private const int networkReachabilityCheckSec = 1;

		private const int multiplayAuthKeyCheckSec = 3600;

		private ServerInfo m_currentServerInfo;

		private SocketIOComponent m_socket;

		private ObscuredString m_authKey;

		private StepBase m_battleStep;

		public int playerCount;

		public int pickCount;

		public int battleUnitCount;

		public int banCount;

		public int timeOverDesync;

		public bool generateControlFlowDesync;

		public bool generateUnitDesync;

		public Action OnConnectedCallback;

		public Action OnDisconnectedCallback;

		public Action OnUserRegisteredCallback;

		public Action OnSocketErrorCallback;

		public Action OnMatchedCallback;

		public Action OnMatchmakingCanceledCallback;

		public Action<bool> OnCheckMatchmakingCallback;

		public Action<FriendlyMatchingRoom> OnFriendlyMatchingRoomCreatedCallback;

		public Action<FriendlyMatchingRoom> OnFriendlyMatchingRoomUpdatedCallback;

		public Action<EnumData.FriendlyMatchingError, JsonData> OnErrorOnFriendlyMatchingCallback;

		public Action<JsonData> OnFriendlyMatchingCallback;

		public Action OnUnitsPickedCallback;

		public Action OnBanSelectedCallback;

		public Action OnFormationSetCallback;

		public Action OnOrderSetCallback;

		public Action OnReadySetCallback;

		public Action OnBattleStartCallback;

		public Action OnEndBattleCallback;

		public Action<ulong, EnumData.MultiplayUserState, int> OnMultiplayUserStateChangedCallback;

		public Action OnWatchMultiplayCallback;

		public Action OnWatchRequestFailedCallback;

		public Action OnWatchFinishedCallback;

		public Action<EnumData.MultiplayInviteUnableReason> OnInviteRequestFailedCallback;

		public Action<string, string> OnInvitedFriendlyMatchCallback;

		public Action OnResponsedInviteFriendlyMatchCallback;

		public Action<Step, JsonData> OnStepResponsedCallback;

		public Action<ulong> OnOpponentRetreatCallback;

		public bool allowWatch;

		private bool m_registered;

		public bool didEmitControl;

		private static MultiplayMode m_mode;

		public static Action OnMultiplayModeChanged;

		private static bool m_isFriendly;

		public static bool isWatchBattleLoading;

		public static string lastScene;

		public ulong watchingHostUid;

		private ulong invitingFriendlyMatchUid;

		public string watchingHostNationName;

		public string watchingBattleKey;

		public static bool willFinishWatch;

		public DateTime dodgePaneltyTime;

		private DeniedInvitingInfo myDeniedInvitingInfo;

		private DeniedInvitingInfo otherDeniedInvitingInfo;

		public BattlePlayData battlePlayData { get; private set; }

		private Step step
		{
			get
			{
				return Step.Match;
			}
		}

		public bool isOpponentOnBattle
		{
			get
			{
				return false;
			}
		}

		public bool isOpponentDisconnected
		{
			get
			{
				return false;
			}
		}

		public bool isConnected
		{
			get
			{
				return false;
			}
		}

		public bool registered
		{
			get
			{
				return false;
			}
		}

		public bool isStepInvalid
		{
			get
			{
				return false;
			}
		}

		public bool isProcessingControl
		{
			get
			{
				return false;
			}
		}

		public static MultiplayMode mode
		{
			get
			{
				return MultiplayMode.Normal;
			}
			set
			{
			}
		}

		public static bool isFriendly
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public static bool isMyActiveBattle
		{
			get
			{
				return false;
			}
		}

		public static bool isNormalBattle
		{
			get
			{
				return false;
			}
		}

		public static bool isContinuedBattle
		{
			get
			{
				return false;
			}
		}

		public static bool isReplayBattle
		{
			get
			{
				return false;
			}
		}

		public static bool isLoadingBattleForWatching
		{
			get
			{
				return false;
			}
		}

		public static bool isWatchingBattle
		{
			get
			{
				return false;
			}
		}

		public static bool isFriendlyBattle
		{
			get
			{
				return false;
			}
		}

		public bool isOnDodgePanelty
		{
			get
			{
				return false;
			}
		}

		public bool IsStep(Step step)
		{
			return false;
		}

		public bool IsStepGTE(Step step)
		{
			return false;
		}

		public bool IsStepComplete(Step step)
		{
			return false;
		}

		public bool CheckDeniedInvitingTime(ulong targetId)
		{
			return false;
		}

		public DateTime GetAbledInvitingTime(ulong targetId)
		{
			return default(DateTime);
		}

		protected override void Awake()
		{
		}

		private void InitBattlePlayData(int battleId)
		{
		}

		public void InitFriendlyBattlePlayData(int battleId)
		{
		}

		public void SetBattlePlayData(BattlePlayData data)
		{
		}

		public static void InitMode(MultiplayMode initMode = MultiplayMode.Normal, bool friendly = false)
		{
		}

		public void ConnectToMultiplayServer()
		{
			// [RE] decompiled: REST /multiplay/getServerInfo first (matchmaker
			// hands out the game server), then Connect().
			HttpRequest.instance.Send("/multiplay/getServerInfo", "POST", true, delegate(JsonData jd)
			{
				if (jd != null && jd.Keys.Contains("socketServerIP"))
				{
					m_currentServerInfo = new ServerInfo();
					m_currentServerInfo.ip = jd["socketServerIP"].ToString();
					m_currentServerInfo.port = jd.Keys.Contains("port") ? (int)jd["port"] : 0;
					m_authKey = jd.Keys.Contains("authKey") ? jd["authKey"].ToString() : string.Empty;
					Connect();
				}
				else if (OnSocketErrorCallback != null)
					OnSocketErrorCallback();
			});
		}

		public void InitBattleStep()
		{
		}

		public void GoToStep(Step step)
		{
		}

		private void Connect()
		{
			if (m_currentServerInfo == null)
				return;
			var go = gameObject;
			if (m_socket == null)
				m_socket = go.AddComponent<SocketIOComponent>();
			m_socket.url = m_currentServerInfo.Uri;
			m_socket.autoConnect = true;
			Register();
			m_socket.Connect();
		}

		public void WillDisconnect()
		{
		}

		public void Disconnect()
		{
		}

		public void DisconnectByQuit()
		{
		}

		private void EmitSocketMessage(string ev, string data)
		{
			// [RE] decompiled: string.Format("{0}{1}", ev, data) → JsonData →
			// Emit — the wire form is ["<ev>", <data>].
			var payload = new JsonData();
			payload["data"] = data;
			if (m_socket != null)
				m_socket.Emit(ev, payload);
		}

		private void Register()
		{
			if (m_socket == null)
				return;
			m_socket.On("connect", delegate(SocketIOEvent e)
			{
				// [RE] login right after connect (SessionImpl parity)
				var payload = new JsonData();
				payload["userId"] = MyUserData.hasInstance ? 0UL : 0UL;
				if (MyUserData.hasInstance)
					payload["userId"] = 0UL;
				payload["authKey"] = (string)m_authKey;
				m_socket.Emit("login", payload);
			});
			m_socket.On("disconnect", delegate(SocketIOEvent e)
			{
				m_socket = null;
				if (OnDisconnectedCallback != null)
					OnDisconnectedCallback();
			});
			m_socket.On("error", delegate(SocketIOEvent e)
			{
				if (OnSocketErrorCallback != null)
					OnSocketErrorCallback();
			});
			m_socket.On("userRegistered", delegate(SocketIOEvent e)
			{
				if (OnUserRegisteredCallback != null)
					OnUserRegisteredCallback();
			});
			// [RE] matchmaking: matched / cancel / room-not-found (event catalog)
			m_socket.On("matched", delegate(SocketIOEvent e)
			{
				if (OnMatchedCallback != null)
					OnMatchedCallback();
			});
			m_socket.On("notFoundRoomId", delegate(SocketIOEvent e)
			{
				if (OnMatchmakingCanceledCallback != null)
					OnMatchmakingCanceledCallback();
			});
			// [RE] battle controls ride the room broadcast → feed the executor
			m_socket.On("battleControl", delegate(SocketIOEvent e)
			{
				var control = new BattleControl();
				control.Deserialize(e.data);
				AddControl(control);
			});
		}

		public void RequestRoomInfo(ulong uid)
		{
		}

		public void UpdateTierPoint(int point, int tierGrade)
		{
		}

		public void SetState(EnumData.MultiplayUserState state)
		{
		}

		public void RequestMatchmaking(int battleId, int mmr, int point, int streak, int tierGrade)
		{
			// [RE] event catalog: want_room_id {room_type} → room_id reply
			var payload = new JsonData();
			payload["room_type"] = "duel";
			if (m_socket != null && m_socket.IsConnected)
			{
				m_socket.Emit("want_room_id", payload);
				if (OnMatchmakingStartedCallback != null)
					OnMatchmakingStartedCallback();
			}
		}

		public bool CheckMatchmaking()
		{
			return false;
		}

		public PanelPopup2Buttons CancelMatchmaking()
		{
			return null;
		}

		public void SendCancelMatchmaking(bool noGame = false, bool dodge = false)
		{
		}

		public void CreateFriendlyMatchingRoom(int battleId, int mmr, int point, int streak, int tierGrade)
		{
		}

		public void RequestFriendlyMatchingRoomInfo(string roomId)
		{
		}

		public void SetComeBackToLobbyOnFriendlyMatching(string roomId)
		{
		}

		public void LeaveFriendlyMatchingRoom(string roomId)
		{
		}

		public void JoinFriendlyMatchingRoom(string roomId, bool byInvitation)
		{
		}

		public void StartFriendlyBattle(string roomId)
		{
		}

		public void FriendlyMatchUpdate(string jsonData)
		{
		}

		public void PickUnits()
		{
		}

		public void SelectBan()
		{
		}

		public void SetFormation()
		{
		}

		public void SetOrder()
		{
		}

		public void SetReady()
		{
		}

		public void StartBattle()
		{
		}

		public void RequestStartBattle()
		{
		}

		public void ContinueBattle()
		{
		}

		public void RequestTurnInfoOnWatch()
		{
		}

		public void EmitTurnStart()
		{
		}

		public void EmitTurnInfo(ulong uid)
		{
		}

		public void EndBattle(bool win)
		{
		}

		public void Retreat()
		{
		}

		public void RetreatContinueBattle()
		{
		}

		public void EmitCheatChangeTurnTime(string turnTimeText)
		{
		}

		public bool EmitControl(BattleControl battleControl, bool ignorePendingState = false)
		{
			return false;
		}

		public void EmitSticker(BattleSticker battleSticker)
		{
		}

		public void EmitRequestMissedControl(int controlIdx)
		{
		}

		public void SetAllowWatch(bool allow)
		{
		}

		public void RequestWatch(ulong hostUid, string battleKey, bool willJoinOnBattleOnly, bool isOnFriendlyMatching = false)
		{
		}

		public void LeaveWatch()
		{
		}

		public void InviteFriendlyMatch(string roomId, ulong targetId)
		{
		}

		private void OnInviteFailed(SocketIOEvent e)
		{
		}

		private void OnInvitedFriendlyMatch(SocketIOEvent e)
		{
		}

		private void OnResponsedInviteFriendlyMatch(SocketIOEvent e)
		{
		}

		private void OnBattleStepOnWatchLoading(SocketIOEvent e)
		{
		}

		public void DenyInvitedFriendlyMatch(string code)
		{
		}

		public void DenyInvitedFriendlyMatch(EnumData.MultiplayInviteUnableReason reason, string code)
		{
		}

		private void DenyInvitedFriendlyMatch(ulong targetId, EnumData.MultiplayInviteUnableReason reason, string roomId)
		{
		}

		public void AcceptInvitedFriendlyMatch(string code)
		{
		}

		public void RequestStep(ulong hostUid, float delay = 0f)
		{
		}

		[DebuggerHidden]
		private IEnumerator RequestStepWithDelay(ulong hostUid, float delay)
		{
			return null;
		}

		public void ResponseStep(ulong targetUid)
		{
		}

		public void CheckMultiplayState()
		{
		}

		public void EmitBattleState(bool timeover = false)
		{
		}

		public void CheckBattleStepOnWatchLoading()
		{
		}

		public void InviteToVoiceChat()
		{
		}

		public void EndVoiceChat()
		{
		}

		private void RegisterCallback()
		{
		}

		private void OnConnected(SocketIOEvent e)
		{
		}

		private void OnDisconnected(SocketIOEvent e)
		{
		}

		private void OnWillDisconnect(SocketIOEvent e)
		{
		}

		private void OnAuthFailed(SocketIOEvent e)
		{
		}

		private void OnRegistered(SocketIOEvent e)
		{
		}

		private void OnMatched(SocketIOEvent e)
		{
		}

		private void OnMessage(SocketIOEvent e)
		{
		}

		private void OnMatchedOnWatch(SocketIOEvent e)
		{
		}

		private void OnMatchedOnFriendlyBattle(SocketIOEvent e)
		{
		}

		private void OnMatchmakingCanceled(SocketIOEvent e)
		{
		}

		private void OnCheckMatchmaking(SocketIOEvent e)
		{
		}

		private void OnFriendlyMatchingRoomCreated(SocketIOEvent e)
		{
		}

		private void OnFriendlyMatchingRoomUpdated(SocketIOEvent e)
		{
		}

		private void OnErrorOnFriendlyMatching(SocketIOEvent e)
		{
		}

		private void OnFriendlyMatching(SocketIOEvent e)
		{
		}

		private void OnContinueFriendlyBattle(SocketIOEvent e)
		{
		}

		private void OnFriendlyBattleEnd(SocketIOEvent e)
		{
		}

		private void OnUnitsPicked(SocketIOEvent e)
		{
		}

		private void OnBanSelected(SocketIOEvent e)
		{
		}

		private void OnFormationSet(SocketIOEvent e)
		{
		}

		private void OnOrderSet(SocketIOEvent e)
		{
		}

		private void OnReadySet(SocketIOEvent e)
		{
		}

		private void OnBattleStart(SocketIOEvent e)
		{
		}

		private void OnStartBattleRequested(SocketIOEvent e)
		{
		}

		private void OnControl(SocketIOEvent e)
		{
		}

		private void OnMissedControls(SocketIOEvent e)
		{
		}

		private void OnControlIgnored(SocketIOEvent e)
		{
		}

		private void OnSticker(SocketIOEvent e)
		{
		}

		private void OnTurnStart(SocketIOEvent e)
		{
		}

		private void OnTurnInfoRequested(SocketIOEvent e)
		{
		}

		private void OnTurnInfo(SocketIOEvent e)
		{
		}

		private void OnTurnInfoRequestedOnWatch(SocketIOEvent e)
		{
		}

		private void OnTurnTimeChanged(SocketIOEvent e)
		{
		}

		private void OnRandomListChanged(SocketIOEvent e)
		{
		}

		private void OnOpponentRetreat(SocketIOEvent e)
		{
		}

		private void OnWatchMultiplay(SocketIOEvent e)
		{
		}

		private void OnWatchRequestFailed(SocketIOEvent e)
		{
		}

		private void OnFinishWatch(SocketIOEvent e)
		{
		}

		public void OnWatchFinishedPostAction(string desc)
		{
		}

		public void OnWatchFinishedPostActionOnLoading()
		{
		}

		private void OnStepRequested(SocketIOEvent e)
		{
		}

		private void OnStepResponsed(SocketIOEvent e)
		{
		}

		private void OnMultiplayRoomStateChanged(SocketIOEvent e)
		{
		}

		private void OnMaintenance(SocketIOEvent e)
		{
		}

		private void OnError(SocketIOEvent e)
		{
		}

		private void OnNeedToUpdate(SocketIOEvent e)
		{
		}

		private void OnCheckMultiplayState(SocketIOEvent e)
		{
		}

		private void OnBattleDesynced(SocketIOEvent e)
		{
		}

		[DebuggerHidden]
		private IEnumerator RollbackBattle(int turnIdx)
		{
			return null;
		}

		private void OnInvitedVoiceChat(SocketIOEvent e)
		{
		}

		private void OnVoiceJoinRoom()
		{
		}

		private void OnEndedVoiceChat(SocketIOEvent e)
		{
		}

		private void ClearCallbacks()
		{
		}

		public void GetServerInfo()
		{
		}

		public void GetMultiplayAuthKey(JsonData response)
		{
		}

		private void RequestMultiplayAuthKey()
		{
		}

		public void OnMultiplayServerInfoRecieved(JsonData response)
		{
		}

		public void OnMultiplayAuthKeyRecieved(JsonData response)
		{
		}

		private void AddResponseHandler()
		{
		}

		private void RemoveResponseHandler()
		{
		}

		private void ShowRetryConnectPopup()
		{
		}

		private void SetBattleEndByOpponentDisconnected()
		{
		}

		public void SetMatchmakingCanceledByInvalidBattlePlayData()
		{
		}

		public void SetMatchmakingCanceledByInvalidRoomInfo()
		{
		}

		private void SetMyBattleEnd(EnumData.MultiplayBattleResult result)
		{
		}

		private void SetOpponentBattleEnd(EnumData.MultiplayBattleResult result)
		{
		}

		public void GoToWorldMap()
		{
		}

		public void GoToCompetitionLobby()
		{
		}

		public void ChangeToLastScene()
		{
		}

		public void SetNextSceneToMove()
		{
		}

		public void SetWin(Action callback = null)
		{
		}

		public void SetLose(Action callback = null)
		{
		}

		private void SetLoseByDisconnected()
		{
		}

		private void SetWinByOpponentRetreat()
		{
		}

		private void SetWinByOpponentDisconnected()
		{
		}

		private void OnConfirmPopup()
		{
		}

		private void ShowCompetitionFinish(bool win)
		{
		}

		public void ShowBattleEndPopupOnWatch(Action callback)
		{
		}

		public void ShowBattleEndPopupOnFriendlyBattle(Action callback)
		{
		}

		[DebuggerHidden]
		private IEnumerator GoToLobbyWithDelay(string title, string localizedMessage, Action callback)
		{
			return null;
		}

		public string GetMyVoiceChatRoomId()
		{
			return null;
		}

		public void JoinMyWatchingHostVoiceChatRoom()
		{
		}

		private void ReconnectToOpenFriendlyMatchingVoiceChatRoom()
		{
		}

		public void OpenFriendlyMatchingVoiceChatRoom()
		{
		}

		private void ReconnectToJoinFriendlyMatchingVoiceChatRoom()
		{
		}

		public void JoinFriendlyMatchingVoiceChatRoom()
		{
		}

		protected override void OnDestroy()
		{
		}

		private void Update()
		{
		}

		protected override void OnApplicationQuit()
		{
		}

		private void OnApplicationPause(bool pause)
		{
		}

		private void CheckNetworkReachability()
		{
		}

		public bool IsBannedUnit(ulong uid, int unitId)
		{
			return false;
		}
	}
}
