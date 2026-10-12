using System;
using System.Diagnostics;

namespace WebSocketSharp
{
	public class LogData
	{
		private StackFrame _caller;

		private DateTime _date;

		private LogLevel _level;

		private string _message;

		public StackFrame Caller
		{
			get
			{
				return null;
			}
		}

		public DateTime Date
		{
			get
			{
				return default(DateTime);
			}
		}

		public LogLevel Level
		{
			get
			{
				return LogLevel.Trace;
			}
		}

		public string Message
		{
			get
			{
				return null;
			}
		}

		internal LogData(LogLevel level, StackFrame caller, string message)
		{
		}

		public override string ToString()
		{
			return null;
		}
	}
}
