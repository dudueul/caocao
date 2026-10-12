namespace HutongGames.PlayMaker.Actions
{
	public class AnimateRect : AnimateFsmAction
	{
		[RequiredField]
		public FsmRect rectVariable;

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
