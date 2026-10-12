namespace HutongGames.PlayMaker.Actions
{
	public class SetColorValue : FsmStateAction
	{
		[RequiredField]
		public FsmColor colorVariable;

		[RequiredField]
		public FsmColor color;

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

		private void DoSetColorValue()
		{
		}
	}
}
