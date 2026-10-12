namespace HutongGames.PlayMaker.Actions
{
	public class ConvertFloatToString : FsmStateAction
	{
		[RequiredField]
		public FsmFloat floatVariable;

		[RequiredField]
		public FsmString stringVariable;

		public FsmString format;

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

		private void DoConvertFloatToString()
		{
		}
	}
}
