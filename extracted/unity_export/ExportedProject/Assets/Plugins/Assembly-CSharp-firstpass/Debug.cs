using System;
using UnityEngine;

public static class Debug
{
	public static bool developerConsoleVisible
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public static bool isDebugBuild
	{
		get
		{
			return false;
		}
	}

	public static void Break()
	{
	}

	public static void ClearDeveloperConsole()
	{
	}

	public static void DebugBreak()
	{
	}

	public static void DrawLine(Vector3 start, Vector3 end)
	{
	}

	public static void DrawLine(Vector3 start, Vector3 end, Color color)
	{
	}

	public static void DrawLine(Vector3 start, Vector3 end, Color color, float duration)
	{
	}

	public static void DrawLine(Vector3 start, Vector3 end, Color color, float duration, bool depthTest)
	{
	}

	public static void DrawRay(Vector3 start, Vector3 dir)
	{
	}

	public static void DrawRay(Vector3 start, Vector3 dir, Color color)
	{
	}

	public static void DrawRay(Vector3 start, Vector3 dir, Color color, float duration)
	{
	}

	public static void DrawRay(Vector3 start, Vector3 dir, Color color, float duration, bool depthTest)
	{
	}

	public static void Log(object message)
	{
	}

	public static void Log(object message, UnityEngine.Object context)
	{
	}

	public static void LogFormat(string format, params object[] args)
	{
	}

	public static void LogWarning(object message)
	{
	}

	public static void LogWarning(object message, UnityEngine.Object context)
	{
	}

	public static void LogError(object message)
	{
	}

	public static void LogError(object message, UnityEngine.Object context)
	{
	}

	public static void LogException(Exception exception)
	{
	}

	public static void LogException(Exception exception, UnityEngine.Object context)
	{
	}

	public static void AddCommand(string methodName, object instance)
	{
	}

	public static void RemoveCommand(string methodName, object instance)
	{
	}
}
