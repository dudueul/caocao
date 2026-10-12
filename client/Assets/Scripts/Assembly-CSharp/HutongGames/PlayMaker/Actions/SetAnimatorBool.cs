using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimatorBool : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString parameter;

		public FsmBool Value;

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

		private void SetParameter()
		{
		}
	}
}
