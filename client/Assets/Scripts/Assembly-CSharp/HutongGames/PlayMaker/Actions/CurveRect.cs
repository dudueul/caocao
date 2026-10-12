using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class CurveRect : CurveFsmAction
	{
		[RequiredField]
		public FsmRect rectVariable;

		[RequiredField]
		public FsmRect fromValue;

		[RequiredField]
		public FsmRect toValue;

		[RequiredField]
		public FsmAnimationCurve curveX;

		public Calculation calculationX;

		[RequiredField]
		public FsmAnimationCurve curveY;

		public Calculation calculationY;

		[RequiredField]
		public FsmAnimationCurve curveW;

		public Calculation calculationW;

		[RequiredField]
		public FsmAnimationCurve curveH;

		public Calculation calculationH;

		private Rect rct;

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
