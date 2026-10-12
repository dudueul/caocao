using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorTarget : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector3 targetPosition;

		public FsmQuaternion targetRotation;

		public FsmGameObject targetGameObject;

		private Animator _animator;

		private Transform _transform;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void DoGetTarget()
		{
		}
	}
}
