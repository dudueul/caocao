using System.Collections.Generic;
using UnityEngine;

namespace MAssist
{
	public class MUnityLogData
	{
		public string message;

		public LogType type;

		public List<MLogStackData> stackDataList;

		public MUnityLogData(string condition, string stackTrace, LogType type)
		{
		}

		private void GetUnityExceptionData(string condition, string stackTrace)
		{
		}
	}
}
