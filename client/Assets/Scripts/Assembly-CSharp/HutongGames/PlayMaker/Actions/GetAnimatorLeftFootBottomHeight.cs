using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorLeftFootBottomHeight : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat leftFootHeight;

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

		private void _getLeftFootBottonHeight()
		{
		}
	}
}
