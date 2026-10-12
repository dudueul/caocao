using System;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

namespace Fabric.Crashlytics.Internal
{
	internal class AndroidImpl : Impl
	{
		public class JavaInteropException : Exception
		{
			public JavaInteropException(string message)
			{
			}
		}

		private readonly List<IntPtr> references;

		private AndroidJavaObject native;

		private AndroidJavaClass crashWrapper;

		private AndroidJavaObject instance;

		private AndroidJavaObject Native
		{
			get
			{
				return null;
			}
		}

		private AndroidJavaClass CrashWrapper
		{
			get
			{
				return null;
			}
		}

		private AndroidJavaObject Instance
		{
			get
			{
				return null;
			}
		}

		public override void Crash()
		{
		}

		public override void Log(string message)
		{
		}

		public override void SetKeyValue(string key, string value)
		{
		}

		public override void SetUserIdentifier(string identifier)
		{
		}

		public override void SetUserEmail(string email)
		{
		}

		public override void SetUserName(string name)
		{
		}

		public override void RecordCustomException(string name, string reason, StackTrace stackTrace)
		{
		}

		public override void RecordCustomException(string name, string reason, string stackTraceString)
		{
		}
	}
}
