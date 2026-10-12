using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorIsHuman : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool isHuman;

		public FsmEvent isHumanEvent;

		public FsmEvent isGenericEvent;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoCheckIsHuman()
		{
		}
	}
}
