using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorPlayBackSpeed : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat playBackSpeed;

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

		private void GetPlayBackSpeed()
		{
		}
	}
}
