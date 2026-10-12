namespace HutongGames.PlayMaker.Actions
{
	public class GetAxis : FsmStateAction
	{
		[RequiredField]
		public FsmString axisName;

		public FsmFloat multiplier;

		[RequiredField]
		public FsmFloat store;

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

		private void DoGetAxis()
		{
		}
	}
}
