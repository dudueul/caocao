namespace HutongGames.PlayMaker.Actions
{
	public class SequenceEvent : FsmStateAction
	{
		public FsmFloat delay;

		public FsmBool reset;

		private DelayedEvent delayedEvent;

		private int eventIndex;

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
