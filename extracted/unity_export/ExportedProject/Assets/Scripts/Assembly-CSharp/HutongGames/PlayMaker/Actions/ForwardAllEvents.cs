namespace HutongGames.PlayMaker.Actions
{
	public class ForwardAllEvents : FsmStateAction
	{
		public FsmEventTarget forwardTo;

		public FsmEvent[] exceptThese;

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
