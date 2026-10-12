using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorIsParameterControlledByCurve : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString parameterName;

		public FsmBool isControlledByCurve;

		public FsmEvent isControlledByCurveEvent;

		public FsmEvent isNotControlledByCurveEvent;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoCheckIsParameterControlledByCurve()
		{
		}
	}
}
