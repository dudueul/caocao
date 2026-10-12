using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimatorBody : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject target;

		public FsmVector3 position;

		public FsmQuaternion rotation;

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

		private void DoSetBody()
		{
		}
	}
}
