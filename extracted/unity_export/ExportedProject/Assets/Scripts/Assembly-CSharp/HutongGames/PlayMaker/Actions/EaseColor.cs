namespace HutongGames.PlayMaker.Actions
{
	public class EaseColor : EaseFsmAction
	{
		[RequiredField]
		public FsmColor fromValue;

		[RequiredField]
		public FsmColor toValue;

		public FsmColor colorVariable;

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
