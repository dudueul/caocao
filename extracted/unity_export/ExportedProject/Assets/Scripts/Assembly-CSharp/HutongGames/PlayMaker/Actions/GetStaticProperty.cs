using System;
using System.Reflection;

namespace HutongGames.PlayMaker.Actions
{
	public class GetStaticProperty : FsmStateAction
	{
		public FsmString className;

		public FsmString propertyName;

		public FsmVar storeResult;

		public bool everyFrame;

		private Type cachedType;

		private string cachedClassName;

		private string cachedPropertyName;

		private PropertyInfo cachedPropertyInfo;

		private string errorString;

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void GetPropertyValue()
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
