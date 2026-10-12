using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class StartCoroutine : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString behaviour;

		[RequiredField]
		public FunctionCall functionCall;

		public bool stopOnExit;

		private MonoBehaviour component;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoStartCoroutine()
		{
		}

		public override void OnExit()
		{
		}
	}
}
