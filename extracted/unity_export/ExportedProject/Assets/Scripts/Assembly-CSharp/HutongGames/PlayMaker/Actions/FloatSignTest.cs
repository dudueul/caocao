namespace HutongGames.PlayMaker.Actions
{
	public class FloatSignTest : FsmStateAction
	{
		[RequiredField]
		public FsmFloat floatValue;

		public FsmEvent isPositive;

		public FsmEvent isNegative;

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

		private void DoSignTest()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
