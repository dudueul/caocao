namespace HutongGames.PlayMaker.Actions
{
	public class FloatSwitch : FsmStateAction
	{
		[RequiredField]
		public FsmFloat floatVariable;

		public FsmFloat[] lessThan;

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

		private void DoFloatSwitch()
		{
		}
	}
}
