using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimatorLayersAffectMassCenter : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool affectMassCenter;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void SetAffectMassCenter()
		{
		}
	}
}
