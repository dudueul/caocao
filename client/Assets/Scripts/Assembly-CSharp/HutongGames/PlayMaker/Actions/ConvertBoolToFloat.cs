namespace HutongGames.PlayMaker.Actions
{
	public class ConvertBoolToFloat : FsmStateAction
	{
		[RequiredField]
		public FsmBool boolVariable;

		[RequiredField]
		public FsmFloat floatVariable;

		public FsmFloat falseValue;

		public FsmFloat trueValue;

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

		private void DoConvertBoolToFloat()
		{
		}
	}
}
