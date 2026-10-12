using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorCurrentStateInfoIsName : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt layerIndex;

		public FsmString name;

		public FsmBool isMatching;

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
