using System;
using System.IO;

namespace MAssist
{
	[Serializable]
	public class MLogFileWriter : MLogWriter
	{
		public enum LogFileType
		{
			JSON = 0,
			XML = 1
		}

		private static string _startTime;

		public string logPath;

		public LogFileType logFileType;

		private string _logPath;

		private StreamWriter _sw;

		private int _index;

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

		private void OnDestroy()
		{
		}
	}
}
