namespace HutongGames.PlayMaker.Actions
{
	public class AnimateFloatV2 : AnimateFsmAction
	{
		[RequiredField]
		public FsmFloat floatVariable;

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
