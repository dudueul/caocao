using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorIKGoal : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmEnum iKGoal;

		public FsmGameObject goal;

		public FsmVector3 position;

		public FsmQuaternion rotation;

		public FsmFloat positionWeight;

		public FsmFloat rotationWeight;

		private Animator _animator;

		private Transform _transform;

		private AvatarIKGoal _iKGoal;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void DoGetIKGoal()
		{
		}
	}
}
