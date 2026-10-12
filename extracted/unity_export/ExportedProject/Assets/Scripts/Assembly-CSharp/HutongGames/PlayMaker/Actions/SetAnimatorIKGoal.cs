using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimatorIKGoal : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public AvatarIKGoal iKGoal;

		public FsmGameObject goal;

		public FsmVector3 position;

		public FsmQuaternion rotation;

		public FsmFloat positionWeight;

		public FsmFloat rotationWeight;

		public bool everyFrame;

		private Animator _animator;

		private Transform _transform;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnEnter()
		{
		}

		public override void DoAnimatorIK(int layerIndex)
		{
		}

		private void DoSetIKGoal()
		{
		}
	}
}
