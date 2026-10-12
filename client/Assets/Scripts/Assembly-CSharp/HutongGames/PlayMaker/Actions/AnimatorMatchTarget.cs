using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class AnimatorMatchTarget : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public AvatarTarget bodyPart;

		public FsmGameObject target;

		public FsmVector3 targetPosition;

		public FsmQuaternion targetRotation;

		public FsmVector3 positionWeight;

		public FsmFloat rotationWeight;

		public FsmFloat startNormalizedTime;

		public FsmFloat targetNormalizedTime;

		public bool everyFrame;

		private Animator _animator;

		private Transform _transform;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoMatchTarget()
		{
		}
	}
}
