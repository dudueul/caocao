namespace HutongGames.PlayMaker.Actions
{
	public class EnumCompare : FsmStateAction
	{
		[RequiredField]
		public FsmEnum enumVariable;

		public FsmEnum compareTo;

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

		private void DoEnumCompare()
		{
		}
	}
}
