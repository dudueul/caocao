using System;
using System.Collections.Generic;
using UnityEngine;

namespace MAssist
{
	[Serializable]
	public class MLogData
	{
		public DateTime date;

		public string dateStr;

		public string message;

		public MLogLevel level;

		public LogCategory category;

		public Exception exception;

		[NonSerialized]
		public UnityEngine.Object context;

		[NonSerialized]
		public object instance;

		public bool isUnityWarn;

		public bool isUnityError;

		public List<MLogStackData> stackDataList;

		public MLogData(object message, MLogLevel level, LogCategory category = LogCategory.Default, object context = null)
		{
		}

		public string ToJSONString(int index)
		{
			return null;
		}

		public string ToXMLString(int index)
		{
			return null;
		}
	}
}
