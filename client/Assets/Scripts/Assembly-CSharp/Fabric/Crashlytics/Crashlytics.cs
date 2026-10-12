using System.Diagnostics;
using Fabric.Crashlytics.Internal;

namespace Fabric.Crashlytics
{
	public class Crashlytics
	{
		private static readonly Impl impl;

		static Crashlytics()
		{
		}

		public static void SetDebugMode(bool debugMode)
		{
		}

		public static void Crash()
		{
		}

		public static void ThrowNonFatal()
		{
		}

		public static void Log(string message)
		{
		}

		public static void SetKeyValue(string key, string value)
		{
		}

		public static void SetUserIdentifier(string identifier)
		{
		}

		public static void SetUserEmail(string email)
		{
		}

		public static void SetUserName(string name)
		{
		}

		public static void RecordCustomException(string name, string reason, StackTrace stackTrace)
		{
		}

		public static void RecordCustomException(string name, string reason, string stackTraceString)
		{
		}
	}
}
