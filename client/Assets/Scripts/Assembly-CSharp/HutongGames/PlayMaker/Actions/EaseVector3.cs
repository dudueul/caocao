namespace HutongGames.PlayMaker.Actions
{
	public class EaseVector3 : EaseFsmAction
	{
		[RequiredField]
		public FsmVector3 fromValue;

		[RequiredField]
		public FsmVector3 toValue;

		public FsmVector3 vector3Variable;

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
