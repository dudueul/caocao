namespace HutongGames.PlayMaker.Actions
{
	public class StringCompare : FsmStateAction
	{
		[RequiredField]
		public FsmString stringVariable;

		public FsmString compareTo;

		public FsmEvent equalEvent;

		public FsmEvent notEqualEvent;

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

		private void DoStringCompare()
		{
		}
	}
}
