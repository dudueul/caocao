using System.Collections;
using System.Diagnostics;
using SocketIO;
using UnityEngine;

public class TestSocketIO : MonoBehaviour
{
	private SocketIOComponent socket;

	public void Start()
	{
	}

	[DebuggerHidden]
	private IEnumerator BeepBoop()
	{
		return null;
	}

	public void TestOpen(SocketIOEvent e)
	{
	}

	public void TestBoop(SocketIOEvent e)
	{
	}

	public void TestError(SocketIOEvent e)
	{
	}

	public void TestClose(SocketIOEvent e)
	{
	}
}
