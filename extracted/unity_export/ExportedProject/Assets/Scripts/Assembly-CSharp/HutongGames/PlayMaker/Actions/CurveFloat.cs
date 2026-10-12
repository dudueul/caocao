namespace HutongGames.PlayMaker.Actions
{
	public class CurveFloat : CurveFsmAction
	{
		[RequiredField]
		public FsmFloat floatVariable;

		[RequiredField]
		public FsmFloat fromValue;

		[RequiredField]
		public FsmFloat toValue;

		[RequiredField]
		public FsmAnimationCurve animCurve;

		public Calculation calculation;

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
