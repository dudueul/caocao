namespace HutongGames.PlayMaker.Actions
{
	public class GetAtan2FromVector2 : FsmStateAction
	{
		[RequiredField]
		public FsmVector2 vector2;

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
