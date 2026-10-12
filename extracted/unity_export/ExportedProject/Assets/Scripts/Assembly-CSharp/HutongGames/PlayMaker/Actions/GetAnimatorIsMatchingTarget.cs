using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorIsMatchingTarget : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool isMatchingActive;

		public FsmEvent matchingActivatedEvent;

		public FsmEvent matchingDeactivedEvent;

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

		private void DoCheckIsMatchingActive()
		{
		}
	}
}
