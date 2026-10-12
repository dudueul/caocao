using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimatorLookAt : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject target;

		public FsmVector3 targetPosition;

		public FsmFloat weight;

		public FsmFloat bodyWeight;

		public FsmFloat headWeight;

		public FsmFloat eyesWeight;

		public FsmFloat clampWeight;

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

		private void DoSetLookAt()
		{
		}
	}
}
