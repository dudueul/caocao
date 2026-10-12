using System.Collections;
using System.Diagnostics;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

internal class AndroidSecuritySlave : ISecuritySlave
{
	private ISecurityMaster m_master;

	private ObscuredInt FLAG_DEBUGGABLE;

	private AndroidJavaObject androidActivity
	{
		get
		{
			return null;
		}
	}

	private AndroidJavaObject packageManager
	{
		get
		{
			return null;
		}
	}

	private ObscuredString DATA_DIR
	{
		get
		{
			return null;
		}
	}

	private ObscuredString NATIVE_LIB_DIR
	{
		get
		{
			return null;
		}
	}

	public void SetMaster(ISecurityMaster master)
	{
	}

	[DebuggerHidden]
	public IEnumerator ComputeHash(HashType hashType)
	{
		return null;
	}

	private ObscuredString GetSignature()
	{
		return null;
	}

	private ObscuredString GetLibunitySo()
	{
		return null;
	}

	private ObscuredString GetLibIl2cppSo()
	{
		return null;
	}

	private ObscuredBool IsEmulator()
	{
		return default(ObscuredBool);
	}

	private ObscuredString GetInstaller()
	{
		return null;
	}

	private ObscuredBool IsDebuggable()
	{
		return default(ObscuredBool);
	}
}
