using UnityEngine;

public class UIGuildWarTopButtons : MonoBehaviour
{
	public GameObject Say;

	public GameObject Listen;

	private GameObject Say_Dim;

	private GameObject Say_Mute;

	private GameObject Say_Disconnect;

	private GameObject Listen_Dim;

	private GameObject Listen_Mute;

	private GameObject Listen_Disconnect;

	private GameObject Listen_Announce;

	private bool IsSayMute;

	private bool IsListenMute;

	private int SpeakingUserCount;

	private bool isDisconnect;

	private float updateTick;

	private float elapsedTime;

	public void Start()
	{
	}

	private void CheckAndConnect()
	{
	}

	private void onPlayerStartSpeaking(ulong id)
	{
	}

	private void onPlayerStopSpeaking(ulong id)
	{
	}

	private void OnDestroy()
	{
	}

	public void UpdateUI()
	{
	}

	private void Update()
	{
	}

	public void OnClickBtnSay()
	{
	}

	public void OnClickBtnListen()
	{
	}

	public void OnClickBattleHistory()
	{
	}
}
