using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class EnableBehaviour : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString behaviour;

		public Component component;

		[RequiredField]
		public FsmBool enable;

		public FsmBool resetOnExit;

		private Behaviour componentTarget;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoEnableBehaviour(GameObject go)
		{
		}

		public override void OnExit()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
