namespace HutongGames.PlayMaker.Actions
{
	public class GetSine : FsmStateAction
	{
		[RequiredField]
		public FsmFloat angle;

		public FsmBool DegToRad;

		[RequiredField]
		public FsmFloat result;

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

		private void DoSine()
		{
		}
	}
}
