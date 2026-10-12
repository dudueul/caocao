namespace HutongGames.PlayMaker.Actions
{
	public class ConvertIntToString : FsmStateAction
	{
		[RequiredField]
		public FsmInt intVariable;

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

		private void DoConvertIntToString()
		{
		}
	}
}
