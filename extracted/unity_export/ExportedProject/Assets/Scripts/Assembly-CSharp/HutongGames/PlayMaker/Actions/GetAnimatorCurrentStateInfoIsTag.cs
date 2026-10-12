using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorCurrentStateInfoIsTag : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt layerIndex;

		public FsmString tag;

		public FsmBool tagMatch;

		public FsmEvent tagMatchEvent;

		public FsmEvent tagDoNotMatchEvent;

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

		private void IsTag()
		{
		}
	}
}
