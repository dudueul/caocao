using System;
using LitJson;
using UnityEngine;

public class PopupCompetitionFriendlyAwait : CaocaoUIModalPopup
{
	private static PopupCompetitionFriendlyAwait self;

	[SerializeField]
	private UILabelLocalized codeLabel;

	[SerializeField]
	private UIFriendlyUserData uiPlayer1;

	[SerializeField]
	private UIFriendlyUserData uiPlayer2;

	[SerializeField]
	private UIFriendlyBattleSetting uiBattleSetting;

	[SerializeField]
	private UILabelLocalized memberCountLabel;

	[SerializeField]
	private UIGrid watcherGrid;

	[SerializeField]
	private UIScrollView watcherScrollView;

	[SerializeField]
	private UILabelLocalized watchersLabelOnWatcher;

	[SerializeField]
	private UILabelLocalized watchersLabel;

	[SerializeField]
	private GameObject playerLabelOnWatcher;

	[SerializeField]
	private GameObject hostBtns;

	[SerializeField]
	private GameObject guestBtns;

	[SerializeField]
	private GameObject hostMoveToBattleSlotBtn;

	[SerializeField]
	private GameObject hostReadyBtn;

	[SerializeField]
	private GameObject hostStartBattleBtn;

	[SerializeField]
	private UILabelLocalized hostReadyBtnLabel;

	[SerializeField]
	private GameObject guestMoveToBattleSlotBtn;

	[SerializeField]
	private GameObject guestReadyBtn;

	[SerializeField]
	private UILabelLocalized guestReadyBtnLabel;

	[SerializeField]
	private GameObject moveToWatcherSlotBtn;

	[SerializeField]
	private GameObject playerFrame;

	private ulong uidToInvite;

	public static bool isAlive
	{
		get
		{
			return false;
		}
	}

	private FriendlyMatchingRoomMember me
	{
		get
		{
			return null;
		}
	}

	private bool isHost
	{
		get
		{
			return false;
		}
	}

	private bool isPlayer
	{
		get
		{
			return false;
		}
	}

	private bool isReady
	{
		get
		{
			return false;
		}
	}

	private FriendlyMatchingRoom roomInfo
	{
		get
		{
			return null;
		}
	}

	private string roomId
	{
		get
		{
			return null;
		}
	}

	public static void Show()
	{
	}

	public static void Show(ulong uidToInvite)
	{
	}

	public static void ShowJoin(FriendlyMatchingRoom roomInfo, bool fromBattle = false)
	{
	}

	private void Init()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateWatcherUI()
	{
	}

	private void UpdatePlayerUI()
	{
	}

	private void UpdateButtons()
	{
	}

	private void OnSelectPlayer(FriendlyMatchingRoomMember member)
	{
	}

	private void OnSelectWatcher(FriendlyMatchingRoomMember member)
	{
	}

	private void MoveToWatchingSlotByHost(FriendlyMatchingRoomMember member)
	{
	}

	private void MoveToPlayerSlotByHost(FriendlyMatchingRoomMember member)
	{
	}

	private void Kickout(FriendlyMatchingRoomMember member)
	{
	}

	private void Entrust(FriendlyMatchingRoomMember member)
	{
	}

	private void OnFriendlyMatchingRoomCreatedCallback(FriendlyMatchingRoom roomInfo)
	{
	}

	private void OnFriendlyMatchingRoomUpdatedCallback(FriendlyMatchingRoom roomInfo)
	{
	}

	private void OnErrorOnFriendlyMatching(EnumData.FriendlyMatchingError error, JsonData jsonData)
	{
	}

	private void OnJoin(JsonData jsonData)
	{
	}

	private void OnMatchingCancel()
	{
	}

	private void OnChangeState(ulong uid, FriendlyMatchingRoomMember.PlayerState state)
	{
	}

	private void OnEditingUnits(ulong editorUid)
	{
	}

	private void OnChangeMap(int mapId, bool isRandom)
	{
	}

	private void OnRandomMapEffectEnd()
	{
	}

	private void OnMoveToWatcher(ulong uid)
	{
	}

	private void OnMoveToPlayer(ulong uid, FriendlyMatchingRoomMember.MemberSlot slot)
	{
	}

	private void OnKickout(ulong uid)
	{
	}

	private void OnEntrust(ulong uid)
	{
	}

	public void OnSocketCallback(JsonData jsonData)
	{
	}

	public void OnDisconnectedCallback()
	{
	}

	public void LeaveFriendlyMatchingRoom()
	{
	}

	public void OnMatched()
	{
	}

	private void RequestUserId(string nationName, Action<ulong> callback)
	{
	}

	private void OnWatchMultiplayCallback()
	{
	}

	private void SendInvitation(ulong userId)
	{
	}

	public void CopyCode()
	{
	}

	public void Ready()
	{
	}

	public void MoveToWatchingSlot()
	{
	}

	public void MoveToBattleSlot()
	{
	}

	public void OpenChattingPopup()
	{
	}

	public void Invite()
	{
	}

	public void StartBattle()
	{
	}

	public void SelectPlayer1()
	{
	}

	public void SelectPlayer2()
	{
	}

	private void SetDeckUnits()
	{
	}

	private bool IsDeckSettingCompleted()
	{
		return false;
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

	protected override void OnDestroy()
	{
	}

	private void RemoveCallback()
	{
	}
}
