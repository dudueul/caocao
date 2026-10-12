namespace HutongGames.PlayMaker.Actions
{
	public class GetAtan2 : FsmStateAction
	{
		[RequiredField]
		public FsmFloat xValue;

		[RequiredField]
		public FsmFloat yValue;

		[RequiredField]
		public FsmFloat angle;

		public FsmBool RadToDeg;

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

		private void DoATan()
		{
		}
	}
}
