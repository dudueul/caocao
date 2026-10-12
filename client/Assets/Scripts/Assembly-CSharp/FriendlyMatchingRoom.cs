using System.Collections.Generic;
using LitJson;

public class FriendlyMatchingRoom
{
	public string roomId;

	public int battleId;

	public List<FriendlyMatchingRoomMember> members;

	public Dictionary<ulong, FriendlyMatchingRoomMember> membersDic;

	public FairRule fairRule;

	public EnumData.FriendlyMatchingRoomState state;

	public int maxMemberCount;

	public bool isChangingMap;

	public FriendlyMatchingRoomMember player1 { get; private set; }

	public FriendlyMatchingRoomMember player2 { get; private set; }

	public FriendlyMatchingRoomMember host { get; private set; }

	public FriendlyMatchingRoomMember me { get; private set; }

	public int watcherCount
	{
		get
		{
			return 0;
		}
	}

	public FriendlyMatchingRoom()
	{
	}

	public FriendlyMatchingRoom(JsonData jsonData)
	{
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	public void DeserializeFairRule(JsonData fairRuleData)
	{
	}

	public bool IsPlayer(FriendlyMatchingRoomMember member)
	{
		return false;
	}

	public bool IsPlayer(ulong uid)
	{
		return false;
	}

	public bool ReleaseReady()
	{
		return false;
	}

	public bool CanStartBattle()
	{
		return false;
	}

	public bool IsAnyPlayerEditingDeck()
	{
		return false;
	}

	public bool MoveToWatcher(ulong uid)
	{
		return false;
	}

	public bool MoveToPlayer(ulong uid, FriendlyMatchingRoomMember.MemberSlot slot)
	{
		return false;
	}

	public bool ChangeState(ulong uid, FriendlyMatchingRoomMember.PlayerState state)
	{
		return false;
	}

	public FriendlyMatchingRoomMember GetWatchHost()
	{
		return null;
	}

	public bool Kickout(ulong uid)
	{
		return false;
	}

	public bool Entrust(ulong uid)
	{
		return false;
	}
}
