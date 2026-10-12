using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class SecurityManager : SceneSingleton<SecurityManager>, ISecurityMaster
{
	private Dictionary<ObscuredString, ObscuredString> m_parameters;

	private ISecuritySlave m_slave;

	private ulong checkSecurityTimeStamp;

	private Action<ObscuredBool, string> m_checkSecurityCallback;

	[DebuggerHidden]
	public IEnumerator ComputeHash(ObscuredString url, Action<ObscuredString> callback)
	{
		return null;
	}

	public void AddParameter(ObscuredString name, ObscuredString value)
	{
	}

	[DebuggerHidden]
	private IEnumerator ComputeHashCoroutine(Action<ObscuredString> callback, ObscuredString url)
	{
		return null;
	}

	private void Awake()
	{
	}

	public void CheckSecurity(Action<ObscuredBool, string> callback)
	{
	}

	[DebuggerHidden]
	private IEnumerator CheckSecurityCoroutine()
	{
		return null;
	}

	private void CheckSecurityCallback(JsonData response)
	{
	}
}
