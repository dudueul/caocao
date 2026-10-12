namespace HutongGames.PlayMaker.Actions
{
	public class IntSwitch : FsmStateAction
	{
		[RequiredField]
		public FsmInt intVariable;

		public FsmInt[] compareTo;

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

		private void DoIntSwitch()
		{
		}
	}
}
