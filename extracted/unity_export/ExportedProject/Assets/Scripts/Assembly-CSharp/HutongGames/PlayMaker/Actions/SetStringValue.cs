namespace HutongGames.PlayMaker.Actions
{
	public class SetStringValue : FsmStateAction
	{
		[RequiredField]
		public FsmString stringVariable;

		public FsmString stringValue;

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

		private void DoSetStringValue()
		{
		}
	}
}
