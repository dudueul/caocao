namespace HutongGames.PlayMaker.Actions
{
	public class EaseRect : EaseFsmAction
	{
		[RequiredField]
		public FsmRect fromValue;

		[RequiredField]
		public FsmRect toValue;

		public FsmRect rectVariable;

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
