namespace HutongGames.PlayMaker.Actions
{
	public class PlayerPrefsHasKey : FsmStateAction
	{
		[RequiredField]
		public FsmString key;

		public FsmBool variable;

		public FsmEvent trueEvent;

		public FsmEvent falseEvent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
