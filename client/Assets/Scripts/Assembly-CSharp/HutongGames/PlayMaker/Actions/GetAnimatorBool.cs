using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorBool : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString parameter;

		[RequiredField]
		public FsmBool result;

		private Animator _animator;

		private int _paramID;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void GetParameter()
		{
		}
	}
}
