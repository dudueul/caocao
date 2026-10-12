namespace HutongGames.PlayMaker.Actions
{
	public class ForwardEvent : FsmStateAction
	{
		public FsmEventTarget forwardTo;

		public FsmEvent[] eventsToForward;

		public bool eatEvents;

		public override void Reset()
		{
		}

		public override bool Event(FsmEvent fsmEvent)
		{
			return false;
		}
	}
}
