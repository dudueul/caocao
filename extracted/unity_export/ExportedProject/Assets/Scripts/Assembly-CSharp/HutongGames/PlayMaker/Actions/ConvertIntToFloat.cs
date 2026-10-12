namespace HutongGames.PlayMaker.Actions
{
	public class ConvertIntToFloat : FsmStateAction
	{
		[RequiredField]
		public FsmInt intVariable;

		[RequiredField]
		public FsmFloat floatVariable;

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

		private void DoConvertIntToFloat()
		{
		}
	}
}
