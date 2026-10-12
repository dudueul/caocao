namespace HutongGames.PlayMaker.Actions
{
	public class StringContains : FsmStateAction
	{
		[RequiredField]
		public FsmString stringVariable;

		[RequiredField]
		public FsmString containsString;

		public FsmEvent trueEvent;

		public FsmEvent falseEvent;

		public FsmBool storeResult;

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

		private void DoStringContains()
		{
		}
	}
}
