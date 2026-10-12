using System;
using UnityEngine;

public class UIFriendlyWatcherListItem : MonoBehaviour
{
	[SerializeField]
	private UILabel hostName;

	[SerializeField]
	private UILabel guestName;

	private FriendlyMatchingRoomMember m_member;

	private Action<FriendlyMatchingRoomMember> onSelectCallback;

	public void SetMember(FriendlyMatchingRoomMember member, Action<FriendlyMatchingRoomMember> callback)
	{
	}

	public void OnSelectWatcher()
	{
	}
}
