using UnityEngine;

public class LabelWatchMemeberCount : MonoBehaviour
{
	private ulong hostUid;

	[SerializeField]
	private UILabel value;

	public void SetHostUid(ulong hostUid)
	{
	}

	private void OnMultiplayUserStateChanged(ulong uid, EnumData.MultiplayUserState state, int memberCnt)
	{
	}

	private void OnDestroy()
	{
	}

	public void SetFriendlyMatchingRoomId(string roomId)
	{
	}

	private void OnFriendlyMatchingRoomUpdatedCallback(FriendlyMatchingRoom roomInfo)
	{
	}
}
