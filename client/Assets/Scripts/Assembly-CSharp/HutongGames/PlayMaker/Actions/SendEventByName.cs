namespace HutongGames.PlayMaker.Actions
{
	public class SendEventByName : FsmStateAction
	{
		public FsmEventTarget eventTarget;

		[RequiredField]
		public FsmString sendEvent;

		public FsmFloat delay;

		public bool everyFrame;

		private DelayedEvent delayedEvent;

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
