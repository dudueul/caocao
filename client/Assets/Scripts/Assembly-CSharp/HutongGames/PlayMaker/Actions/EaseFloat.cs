namespace HutongGames.PlayMaker.Actions
{
	public class EaseFloat : EaseFsmAction
	{
		[RequiredField]
		public FsmFloat fromValue;

		[RequiredField]
		public FsmFloat toValue;

		public FsmFloat floatVariable;

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
