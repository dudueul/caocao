namespace HutongGames.PlayMaker.Actions
{
	public class BoolAnyTrue : FsmStateAction
	{
		[RequiredField]
		public FsmBool[] boolVariables;

		public FsmEvent sendEvent;

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

		private void DoAnyTrue()
		{
		}
	}
}
