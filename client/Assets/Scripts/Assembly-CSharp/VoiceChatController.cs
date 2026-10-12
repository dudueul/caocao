using System;
using System.Collections;
using System.Diagnostics;
using LitJson;

public class VoiceChatController : Singleton<VoiceChatController>
{
	private string roomName;

	public Action onChangeChannelCallback;

	public Action<ulong, float> onLocalPlayerSpeechAmplitudeCallback;

	public Action onOptionChangeCallback;

	private VoiceEventHandler eventHandler;

	private VoiceNetworkStatus netStatus;

	public string RoomName
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

	public bool IsConnecting
	{
		get
		{
			return false;
		}
	}

	public event Action SocketConnected
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action ConnectionFail
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action SocketDisconnect
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action LeftTheRoom
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<ulong> PlayerStartedSpeaking
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<ulong> PlayerStoppedSpeaking
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<ulong, string> PlayerEnteredRoom
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<ulong, string> PlayerExitedRoom
	{
		add
		{
		}
		remove
		{
		}
	}

	protected override void Awake()
	{
	}

	public void OpenRoom(EnumData.VoiceChatUseScene openRoomScene, params object[] args)
	{
	}

	public void JoinRoom(EnumData.VoiceChatUseScene useScene, params object[] args)
	{
	}

	public void LeaveRoom()
	{
	}

	public int GetNumberOfRoomListening()
	{
		return 0;
	}

	public void ChangeChannel(EnumData.VoiceChatUseScene scene, int channel)
	{
	}

	public void MuteSpecificUser(string setMuteUserId, bool toggle)
	{
	}

	public void Mute(bool toggle)
	{
	}

	public void Deafened(bool toggle)
	{
	}

	private void SetBroadcastVolume()
	{
	}

	private void SetReceiptVolume()
	{
	}

	public void MuteInUserOption(bool toggle)
	{
	}

	public void DeafenedInUserOption(bool toggle)
	{
	}

	public void SetVolumeSpeaking(float setVolume)
	{
	}

	public void SetVolumeListening(float setVolume)
	{
	}

	private void RequestServerInfo()
	{
	}

	private void OnServerInfoRecieved(JsonData response)
	{
	}

	public void RequestPermission()
	{
	}

	[DebuggerHidden]
	private IEnumerator RequestIOSAuthorization()
	{
		return null;
	}

	private void OnPermissionGranted()
	{
	}

	private void OnPermissionDeny()
	{
	}

	private void InitDissonance()
	{
	}

	private void CheckMicrophone()
	{
	}

	private void NetworkDisconnect()
	{
	}

	private void NetworkConnecting()
	{
	}

	private void NetworkConnect()
	{
	}

	private void NetworkConnectFail()
	{
	}

	public bool CheckServerOption(EnumData.VoiceChatUseScene useScene)
	{
		return false;
	}

	private void AddDissonanceEvents()
	{
	}

	private void RemoveDissonanceEvents()
	{
	}

	private void OnSocketConnected()
	{
	}

	private void OnSocketConnectionFail()
	{
	}

	private void OnSocketDisconnected()
	{
	}
}
