using UnityEngine;

public class WatcherNumLabel : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized label;

	private void Start()
	{
	}

	private void OnBattleStateChanged(ulong uid, EnumData.MultiplayUserState state, int memberCnt)
	{
	}

	private void UpdateLabel(int memberCnt)
	{
	}

	private void OnFriendlyMatchingRoomUpdatedCallback(FriendlyMatchingRoom roomInfo)
	{
	}

	private void OnDestroy()
	{
	}
}
