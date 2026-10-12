namespace HutongGames.PlayMaker.Actions
{
	public class SendRandomEvent : FsmStateAction
	{
		public FsmEvent[] events;

		public FsmFloat[] weights;

		public FsmFloat delay;

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
