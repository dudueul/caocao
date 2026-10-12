namespace HutongGames.PlayMaker.Actions
{
	public class RandomEvent : FsmStateAction
	{
		public FsmFloat delay;

		public FsmBool noRepeat;

		private DelayedEvent delayedEvent;

		private int randomEventIndex;

		private int lastEventIndex;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private FsmEvent GetRandomEvent()
		{
			return null;
		}
	}
}
