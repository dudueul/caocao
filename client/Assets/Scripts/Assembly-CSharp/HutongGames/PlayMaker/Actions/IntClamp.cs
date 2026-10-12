namespace HutongGames.PlayMaker.Actions
{
	public class IntClamp : FsmStateAction
	{
		[RequiredField]
		public FsmInt intVariable;

		[RequiredField]
		public FsmInt minValue;

		[RequiredField]
		public FsmInt maxValue;

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

		private void DoClamp()
		{
		}
	}
}
