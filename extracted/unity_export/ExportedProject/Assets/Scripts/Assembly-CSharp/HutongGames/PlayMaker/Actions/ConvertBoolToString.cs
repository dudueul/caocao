namespace HutongGames.PlayMaker.Actions
{
	public class ConvertBoolToString : FsmStateAction
	{
		[RequiredField]
		public FsmBool boolVariable;

		[RequiredField]
		public FsmString stringVariable;

		public FsmString falseString;

		public FsmString trueString;

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

		private void DoConvertBoolToString()
		{
		}
	}
}
