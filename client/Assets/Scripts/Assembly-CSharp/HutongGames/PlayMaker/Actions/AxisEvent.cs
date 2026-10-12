namespace HutongGames.PlayMaker.Actions
{
	public class AxisEvent : FsmStateAction
	{
		public FsmString horizontalAxis;

		public FsmString verticalAxis;

		public FsmEvent leftEvent;

		public FsmEvent rightEvent;

		public FsmEvent upEvent;

		public FsmEvent downEvent;

		public FsmEvent anyDirection;

		public FsmEvent noDirection;

		public override void Reset()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
