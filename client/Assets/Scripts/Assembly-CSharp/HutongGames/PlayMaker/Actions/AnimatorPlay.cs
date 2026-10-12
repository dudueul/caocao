using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class AnimatorPlay : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString stateName;

		public FsmInt layer;

		public FsmFloat normalizedTime;

		public bool everyFrame;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoAnimatorPlay()
		{
		}
	}
}
