using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorRightFootBottomHeight : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat rightFootHeight;

		public bool everyFrame;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnLateUpdate()
		{
		}

		private void _getRightFootBottonHeight()
		{
		}
	}
}
