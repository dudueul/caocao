using System;
using UnityEngine;

namespace Fabric.Internal.Crashlytics
{
	public class CrashlyticsInit : MonoBehaviour
	{
		private static readonly string kitName;

		private static CrashlyticsInit instance;

		private void Awake()
		{
		}

		private void AwakeOnce()
		{
		}

		private static void RegisterExceptionHandlers()
		{
		}

		private static bool IsSDKInitialized()
		{
			return false;
		}

		private static void HandleException(object sender, UnhandledExceptionEventArgs eArgs)
		{
		}

		private static void HandleLog(string message, string stackTraceString, LogType type)
		{
		}

		private static string[] getMessageParts(string message)
		{
			return null;
		}
	}
}
