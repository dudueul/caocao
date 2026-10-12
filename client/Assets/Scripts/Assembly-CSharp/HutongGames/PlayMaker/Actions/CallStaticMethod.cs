using System;
using System.Reflection;

namespace HutongGames.PlayMaker.Actions
{
	public class CallStaticMethod : FsmStateAction
	{
		public FsmString className;

		public FsmString methodName;

		public FsmVar[] parameters;

		public FsmVar storeResult;

		public bool everyFrame;

		private Type cachedType;

		private string cachedClassName;

		private string cachedMethodName;

		private MethodInfo cachedMethodInfo;

		private ParameterInfo[] cachedParameterInfo;

		private object[] parametersArray;

		private string errorString;

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoMethodCall()
		{
		}

		private bool DoCache()
		{
			return false;
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
