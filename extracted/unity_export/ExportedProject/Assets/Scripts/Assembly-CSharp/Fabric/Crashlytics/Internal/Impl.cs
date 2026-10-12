using System.Collections.Generic;
using System.Diagnostics;

namespace Fabric.Crashlytics.Internal
{
	internal class Impl
	{
		private delegate Dictionary<string, string> FrameParser(string frameString);

		protected const string KitName = "Crashlytics";

		private static readonly string FrameArgsRegex;

		private static readonly string FrameRegexWithoutFileInfo;

		private static readonly string FrameRegexWithFileInfo;

		private static readonly string MonoFilenameUnknownString;

		private static readonly string[] StringDelimiters;

		public static Impl Make()
		{
			return null;
		}

		public virtual void SetDebugMode(bool mode)
		{
		}

		public virtual void Crash()
		{
		}

		public virtual void ThrowNonFatal()
		{
		}

		public virtual void Log(string message)
		{
		}

		public virtual void SetKeyValue(string key, string value)
		{
		}

		public virtual void SetUserIdentifier(string identifier)
		{
		}

		public virtual void SetUserEmail(string email)
		{
		}

		public virtual void SetUserName(string name)
		{
		}

		public virtual void RecordCustomException(string name, string reason, StackTrace stackTrace)
		{
		}

		public virtual void RecordCustomException(string name, string reason, string stackTraceString)
		{
		}

		private static Dictionary<string, string> ParseFrameString(string regex, string frameString)
		{
			return null;
		}

		protected static Dictionary<string, string>[] ParseStackTraceString(string stackTraceString)
		{
			return null;
		}
	}
}
