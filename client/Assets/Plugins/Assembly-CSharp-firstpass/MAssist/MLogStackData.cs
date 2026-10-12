using System;
using System.Diagnostics;

namespace MAssist
{
	[Serializable]
	public class MLogStackData
	{
		public string filePath;

		public string shortMethodName;

		public string fullMethodName;

		public int line;

		public MLogStackData(StackFrame frame)
		{
		}

		public MLogStackData(string filePath, string methodName, int line)
		{
		}

		private string GetSourceCode(string filePath, int line, int extentionLine)
		{
			return null;
		}
	}
}
