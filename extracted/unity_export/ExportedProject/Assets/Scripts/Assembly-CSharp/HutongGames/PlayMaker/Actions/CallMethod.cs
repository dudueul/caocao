using System;
using System.Reflection;

namespace HutongGames.PlayMaker.Actions
{
	public class CallMethod : FsmStateAction
	{
		public FsmObject behaviour;

		public FsmString methodName;

		public FsmVar[] parameters;

		public FsmVar storeResult;

		public bool everyFrame;

		public bool manualUI;

		private FsmObject cachedBehaviour;

		private FsmString cachedMethodName;

		private Type cachedType;

		private MethodInfo cachedMethodInfo;

		private ParameterInfo[] cachedParameterInfo;

		private object[] parametersArray;

		private string errorString;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoMethodCall()
		{
		}

		private bool NeedToUpdateCache()
		{
			return false;
		}

		private void ClearCache()
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
