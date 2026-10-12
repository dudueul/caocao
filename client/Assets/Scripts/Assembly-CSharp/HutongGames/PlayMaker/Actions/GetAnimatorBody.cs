using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorBody : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector3 bodyPosition;

		public FsmQuaternion bodyRotation;

		public FsmGameObject bodyGameObject;

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

		private void DoGetBodyPosition()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
