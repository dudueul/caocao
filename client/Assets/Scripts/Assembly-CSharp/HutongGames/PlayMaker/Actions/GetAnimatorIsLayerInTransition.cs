using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorIsLayerInTransition : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt layerIndex;

		public FsmBool isInTransition;

		public FsmEvent isInTransitionEvent;

		public FsmEvent isNotInTransitionEvent;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void DoCheckIsInTransition()
		{
		}
	}
}
