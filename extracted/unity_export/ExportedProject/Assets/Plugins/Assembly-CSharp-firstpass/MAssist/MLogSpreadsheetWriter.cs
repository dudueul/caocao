using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using M;

namespace MAssist
{
	[Serializable]
	public class MLogSpreadsheetWriter : MLogWriter
	{
		private const int MaxSendingItemNum = 50;

		private const int MaxRetryNum = 5;

		private const float TimeoutDelay = 10f;

		public string logPath;

		public string scriptURL;

		private static string _startTime;

		public static string spreadsheetID;

		private LogWriterState _state;

		private int _currentRetry;

		private float _endTime;

		private Coroutiner _coroutiner;

		private string _logPath;

		private int _index;

		private Queue<string> _reservationLogDataStringList;

		[DebuggerHidden]
		private IEnumerator initSpreadsheet()
		{
			return null;
		}

		[DebuggerHidden]
		private IEnumerator loopSendLog()
		{
			return null;
		}

		private void setLogPath()
		{
		}

		public override void Init()
		{
		}

		public override void SetDefault()
		{
		}

		public override void Write(MLogData data)
		{
		}
	}
}
