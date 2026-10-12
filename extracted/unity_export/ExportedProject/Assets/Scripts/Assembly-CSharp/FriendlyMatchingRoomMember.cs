using LitJson;

public class FriendlyMatchingRoomMember
{
	public enum MemberSlot
	{
		Player1 = 0,
		Player2 = 1,
		Watcher = 2
	}

	public enum PlayerState
	{
		Standby = 0,
		EditingDeck = 1,
		Ready = 2,
		Battle = 3,
		Watching = 4,
		Disconnected = 5
	}

	public ulong uid;

	public string nationName;

	public int faceUnitId;

	public int skinItemId;

	public bool isHost;

	public PlayerState state;

	public MemberSlot slot;

	public string enterDate;

	public bool isReady
	{
		get
		{
			return false;
		}
	}

	public bool isEditingDeck
	{
		get
		{
			return false;
		}
	}

	public bool isInLobby
	{
		get
		{
			return false;
		}
	}

	public FriendlyMatchingRoomMember()
	{
	}

	public FriendlyMatchingRoomMember(JsonData jsonData)
	{
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	public static int CompareFunc(FriendlyMatchingRoomMember a, FriendlyMatchingRoomMember b)
	{
		return 0;
	}
}
