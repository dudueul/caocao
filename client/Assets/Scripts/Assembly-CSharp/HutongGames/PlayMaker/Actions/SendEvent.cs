namespace HutongGames.PlayMaker.Actions
{
	public class SendEvent : FsmStateAction
	{
		public FsmEventTarget eventTarget;

		[RequiredField]
		public FsmEvent sendEvent;

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
