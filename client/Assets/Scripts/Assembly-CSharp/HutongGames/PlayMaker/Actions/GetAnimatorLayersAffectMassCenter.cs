using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorLayersAffectMassCenter : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool affectMassCenter;

		public FsmEvent affectMassCenterEvent;

		public FsmEvent doNotAffectMassCenterEvent;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void CheckAffectMassCenter()
		{
		}
	}
}
