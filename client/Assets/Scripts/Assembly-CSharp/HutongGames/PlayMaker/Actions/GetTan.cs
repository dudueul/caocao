namespace HutongGames.PlayMaker.Actions
{
	public class GetTan : FsmStateAction
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

		private void DoTan()
		{
		}
	}
}
