using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class CurveColor : CurveFsmAction
	{
		[RequiredField]
		public FsmColor colorVariable;

		[RequiredField]
		public FsmColor fromValue;

		[RequiredField]
		public FsmColor toValue;

		[RequiredField]
		public FsmAnimationCurve curveR;

		public Calculation calculationR;

		[RequiredField]
		public FsmAnimationCurve curveG;

		public Calculation calculationG;

		[RequiredField]
		public FsmAnimationCurve curveB;

		public Calculation calculationB;

		[RequiredField]
		public FsmAnimationCurve curveA;

		public Calculation calculationA;

		private Color clr;

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
