namespace HutongGames.PlayMaker.Actions
{
	public class IntCompare : FsmStateAction
	{
		[RequiredField]
		public FsmInt integer1;

		[RequiredField]
		public FsmInt integer2;

		public FsmEvent equal;

		public FsmEvent lessThan;

		public FsmEvent greaterThan;

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

		private void DoIntCompare()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
