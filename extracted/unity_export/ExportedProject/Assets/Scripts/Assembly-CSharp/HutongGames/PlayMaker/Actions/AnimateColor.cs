namespace HutongGames.PlayMaker.Actions
{
	public class AnimateColor : AnimateFsmAction
	{
		[RequiredField]
		public FsmColor colorVariable;

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

		private bool finishInNextStep;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void UpdateVariableValue()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
