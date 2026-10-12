using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimatorPlayBackTime : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat playbackTime;

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

		private void DoPlaybackTime()
		{
		}
	}
}
