namespace HutongGames.PlayMaker.Actions
{
	public class ConvertBoolToColor : FsmStateAction
	{
		[RequiredField]
		public FsmBool boolVariable;

		[RequiredField]
		public FsmColor colorVariable;

		public FsmColor falseColor;

		public FsmColor trueColor;

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

		private void DoConvertBoolToColor()
		{
		}
	}
}
