using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public abstract class BaseFsmVariableIndexAction : FsmStateAction
	{
		public FsmEvent indexOutOfRange;

		public FsmEvent fsmNotFound;

		public FsmEvent variableNotFound;

		private GameObject cachedGameObject;

		private string cachedFsmName;

		protected PlayMakerFSM fsm;

		public override void Reset()
		{
		}

		protected bool UpdateCache(GameObject go, string fsmName)
		{
			return false;
		}

		protected void DoVariableNotFound(string variableName)
		{
		}
	}
}
