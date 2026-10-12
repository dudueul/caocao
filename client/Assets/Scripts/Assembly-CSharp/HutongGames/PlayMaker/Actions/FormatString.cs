namespace HutongGames.PlayMaker.Actions
{
	public class FormatString : FsmStateAction
	{
		[RequiredField]
		public FsmString format;

		public FsmVar[] variables;

		[RequiredField]
		public FsmString storeResult;

		public bool everyFrame;

		private object[] objectArray;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoFormatString()
		{
		}
	}
}
