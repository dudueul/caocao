using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class CurveVector3 : CurveFsmAction
	{
		[RequiredField]
		public FsmVector3 vectorVariable;

		[RequiredField]
		public FsmVector3 fromValue;

		[RequiredField]
		public FsmVector3 toValue;

		[RequiredField]
		public FsmAnimationCurve curveX;

		public Calculation calculationX;

		[RequiredField]
		public FsmAnimationCurve curveY;

		public Calculation calculationY;

		[RequiredField]
		public FsmAnimationCurve curveZ;

		public Calculation calculationZ;

		private Vector3 vct;

		private bool finishInNextStep;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
