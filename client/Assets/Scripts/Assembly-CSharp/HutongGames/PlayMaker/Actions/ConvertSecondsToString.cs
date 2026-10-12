namespace HutongGames.PlayMaker.Actions
{
	public class ConvertSecondsToString : FsmStateAction
	{
		[RequiredField]
		public FsmFloat secondsVariable;

		[RequiredField]
		public FsmString stringVariable;

		[RequiredField]
		public FsmString format;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoConvertSecondsToString()
		{
		}
	}
}
