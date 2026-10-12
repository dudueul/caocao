namespace HutongGames.PlayMaker.Actions
{
	public class StringReplace : FsmStateAction
	{
		[RequiredField]
		public FsmString stringVariable;

		public FsmString replace;

		public FsmString with;

		[RequiredField]
		public FsmString storeResult;

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

		private void DoReplace()
		{
		}
	}
}
