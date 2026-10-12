using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorDelta : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector3 deltaPosition;

		public FsmQuaternion deltaRotation;

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

		private void DoGetDeltaPosition()
		{
		}
	}
}
