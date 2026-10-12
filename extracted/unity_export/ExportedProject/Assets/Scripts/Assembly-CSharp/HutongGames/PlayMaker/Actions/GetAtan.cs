namespace HutongGames.PlayMaker.Actions
{
	public class GetAtan : FsmStateAction
	{
		[RequiredField]
		public FsmFloat Value;

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
