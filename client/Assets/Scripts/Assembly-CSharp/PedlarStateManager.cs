using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class PedlarStateManager : Singleton<PedlarStateManager>
{
	public enum State
	{
		Ready = 0,
		Instance = 1,
		Store = 2,
		Cooltime = 3
	}

	public enum PopupState
	{
		InstanceFound = 0,
		PedlarRescued = 1,
		PedlarDespoiled = 2
	}

	public static int PEDLAR_INSTANCE_TYPE;

	public DateTime packManShopLimit;

	private bool isProcessing;

	public bool newPedlarInstanceOpened { get; private set; }

	public bool isPackManAvailable
	{
		get
		{
			return false;
		}
	}

	public void CheckPedlarConditionAndProcess(HttpRequest.ResponseCallback callback)
	{
	}

	public void CheckPedlarFocibly(HttpRequest.ResponseCallback callback)
	{
	}

	public void CheckPedlarInstances(HttpRequest.ResponseCallback callback, bool forcibly = false)
	{
	}

	[DebuggerHidden]
	public IEnumerator ShowPedlarInstanceFoundEffect()
	{
		return null;
	}

	public GameObject ShowPedlarPopup(PopupState popupState)
	{
		return null;
	}

	public UserInstance GetPedlarUserInstance()
	{
		return null;
	}

	public UserInstance GetActivePackManUserInstance()
	{
		return null;
	}
}
