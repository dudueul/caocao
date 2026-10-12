namespace HutongGames.PlayMaker.Actions
{
	public class StringChanged : FsmStateAction
	{
		[RequiredField]
		public FsmString stringVariable;

		public FsmEvent changedEvent;

		public FsmBool storeResult;

		private string previousValue;

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
