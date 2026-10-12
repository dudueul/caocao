using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorSpeed : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat speed;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void GetPlaybackSpeed()
		{
		}
	}
}
