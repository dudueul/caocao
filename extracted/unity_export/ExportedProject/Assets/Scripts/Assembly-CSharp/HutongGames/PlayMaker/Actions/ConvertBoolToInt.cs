namespace HutongGames.PlayMaker.Actions
{
	public class ConvertBoolToInt : FsmStateAction
	{
		[RequiredField]
		public FsmBool boolVariable;

		[RequiredField]
		public FsmInt intVariable;

		public FsmInt falseValue;

		public FsmInt trueValue;

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

		private void DoConvertBoolToInt()
		{
		}
	}
}
