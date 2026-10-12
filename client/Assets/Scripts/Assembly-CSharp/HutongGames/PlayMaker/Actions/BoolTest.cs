namespace HutongGames.PlayMaker.Actions
{
	public class BoolTest : FsmStateAction
	{
		[RequiredField]
		[Readonly]
		public FsmBool boolVariable;

		public FsmEvent isTrue;

		public FsmEvent isFalse;

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
	}
}
