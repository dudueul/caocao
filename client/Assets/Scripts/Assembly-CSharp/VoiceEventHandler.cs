using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Runtime.InteropServices;

public class VoiceEventHandler
{
	private enum EventType
	{
		Connected = 0,
		ConnectionFail = 1,
		Disconnect = 2,
		LeaveRoom = 3,
		PlayerStartedSpeaking = 4,
		PlayerStoppedSpeaking = 5,
		PlayerEnteredRoom = 6,
		PlayerExitedRoom = 7
	}

	[StructLayout((LayoutKind)0, Size = 24)]
	private struct Event
	{
		public readonly EventType Type;

		public readonly ulong playerUID;

		public readonly string roomName;

		public Event(EventType setType)
		{
			Type = EventType.Connected;
			playerUID = 0uL;
			roomName = null;
		}

		public Event(EventType setType, string setPlayerName)
		{
			Type = EventType.Connected;
			playerUID = 0uL;
			roomName = null;
		}

		public Event(EventType setType, string setPlayerName, string setRoomName)
		{
			Type = EventType.Connected;
			playerUID = 0uL;
			roomName = null;
		}
	}

	private List<Event> listEvent;

	private Action SocketConnected__BackingField;

	private Action SocketDisconnect__BackingField;

	private Action ConnectionFail__BackingField;

	private Action LeaveRoom__BackingField;

	private Action<ulong> PlayerStartedSpeaking__BackingField;

	private Action<ulong> PlayerStoppedSpeaking__BackingField;

	private Action<ulong, string> PlayerEnteredRoom__BackingField;

	private Action<ulong, string> PlayerExitedRoom__BackingField;

	public event Action SocketConnected
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

	public event Action ConnectionFail
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action LeaveRoom
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

	public void AddConnected()
	{
	}

	public void AddConnectionFail()
	{
	}

	public void AddDisconnected()
	{
	}

	public void AddLeaveRoom()
	{
	}

	public void AddPlayerStartedSpeaking(string speakerName)
	{
	}

	public void AddPlayerStoppedSpeaking(string speakerName)
	{
	}

	public void AddPlayerEnteredRoom(string entererName, string enterRoomName)
	{
	}

	public void AddPlayerExitedRoom(string exitPlayerName, string exitRoomName)
	{
	}

	[DebuggerHidden]
	public IEnumerator CheckEvent()
	{
		return null;
	}

	private void InvokeEvent(Action handler)
	{
	}

	private void InvokeEvent<T1>(T1 arg1, Action<T1> handler)
	{
	}

	private void InvokeEvent<T1, T2>(T1 arg1, T2 arg2, Action<T1, T2> handler)
	{
	}
}
