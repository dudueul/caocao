namespace HutongGames.PlayMaker.Actions
{
	public class ConvertEnumToString : FsmStateAction
	{
		[RequiredField]
		public FsmEnum enumVariable;

		[RequiredField]
		public FsmString stringVariable;

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

		private void DoConvertEnumToString()
		{
		}
	}
}
