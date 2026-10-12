using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorCurrentTransitionInfoIsUserName : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt layerIndex;

		public FsmString userName;

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
