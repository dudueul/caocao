namespace HutongGames.PlayMaker.Actions
{
	public class IntChanged : FsmStateAction
	{
		[RequiredField]
		public FsmInt intVariable;

		public FsmEvent changedEvent;

		public FsmBool storeResult;

		private int previousValue;

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
