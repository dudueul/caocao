namespace HutongGames.PlayMaker.Actions
{
	public class StringSwitch : FsmStateAction
	{
		[RequiredField]
		public FsmString stringVariable;

		public FsmString[] compareTo;

		public FsmEvent[] sendEvent;

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

		private void DoStringSwitch()
		{
		}
	}
}
