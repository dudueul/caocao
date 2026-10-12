namespace HutongGames.PlayMaker.Actions
{
	public class Wait : FsmStateAction
	{
		[RequiredField]
		public FsmFloat time;

		public FsmEvent finishEvent;

		public bool realTime;

		private float startTime;

		private float timer;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
