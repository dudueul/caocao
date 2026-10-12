using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorCurrentTransitionInfoIsName : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt layerIndex;

		public FsmString name;

		public FsmBool nameMatch;

		public FsmEvent nameMatchEvent;

		public FsmEvent nameDoNotMatchEvent;

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

		private void IsName()
		{
		}
	}
}
