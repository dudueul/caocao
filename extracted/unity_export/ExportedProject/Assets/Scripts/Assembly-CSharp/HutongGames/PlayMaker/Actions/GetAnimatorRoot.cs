using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorRoot : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector3 rootPosition;

		public FsmQuaternion rootRotation;

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
	}
}
