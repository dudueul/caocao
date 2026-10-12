using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class AddScript : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString script;

		public FsmBool removeOnExit;

		private Component addedComponent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		private void DoAddComponent(GameObject go)
		{
		}
	}
}
