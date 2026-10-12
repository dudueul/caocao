using System;
using System.Reflection;

namespace MAssist
{
	public class MCommandItem
	{
		private string _methodName;

		private object _instance;

		private Type _instanceType;

		private MethodInfo _methodInfo;

		public MCommandItem(string methodName, object instance)
		{
		}

		public bool IsEqual(string methodeName, object instance)
		{
			return false;
		}

		public void OnGUI()
		{
		}
	}
}
