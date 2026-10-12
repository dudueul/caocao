namespace HutongGames.PlayMaker.Actions
{
	public class GetStringRight : FsmStateAction
	{
		[RequiredField]
		public FsmString stringVariable;

		public FsmInt charCount;

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

		private void DoGetStringRight()
		{
		}
	}
}
