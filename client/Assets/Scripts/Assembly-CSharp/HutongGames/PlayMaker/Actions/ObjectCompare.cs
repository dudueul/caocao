namespace HutongGames.PlayMaker.Actions
{
	public class ObjectCompare : FsmStateAction
	{
		[RequiredField]
		[Readonly]
		public FsmObject objectVariable;

		[RequiredField]
		public FsmObject compareTo;

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

		private void DoObjectCompare()
		{
		}
	}
}
