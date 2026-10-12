namespace HutongGames.PlayMaker.Actions
{
	public class AnimateVector3 : AnimateFsmAction
	{
		[RequiredField]
		public FsmVector3 vectorVariable;

		[RequiredField]
		public FsmAnimationCurve curveX;

		public Calculation calculationX;

		[RequiredField]
		public FsmAnimationCurve curveY;

		public Calculation calculationY;

		[RequiredField]
		public FsmAnimationCurve curveZ;

		public Calculation calculationZ;

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
