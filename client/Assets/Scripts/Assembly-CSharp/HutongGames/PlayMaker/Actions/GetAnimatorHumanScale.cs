using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorHumanScale : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat humanScale;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetHumanScale()
		{
		}
	}
}
