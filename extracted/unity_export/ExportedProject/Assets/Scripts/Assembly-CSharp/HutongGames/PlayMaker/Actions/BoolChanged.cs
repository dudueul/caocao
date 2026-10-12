namespace HutongGames.PlayMaker.Actions
{
	public class BoolChanged : FsmStateAction
	{
		[RequiredField]
		public FsmBool boolVariable;

		public FsmEvent changedEvent;

		public FsmBool storeResult;

		private bool previousValue;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
