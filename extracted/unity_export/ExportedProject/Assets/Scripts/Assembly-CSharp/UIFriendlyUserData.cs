using UnityEngine;

public class UIFriendlyUserData : MonoBehaviour
{
	[SerializeField]
	private UITexture face;

	[SerializeField]
	private UILabel labelNation;

	[SerializeField]
	private GameObject objHost;

	[SerializeField]
	private GameObject objReady;

	public ulong uid { get; set; }

	public void SetData(FriendlyMatchingRoomMember member)
	{
	}
}
