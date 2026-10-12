namespace HutongGames.PlayMaker.Actions
{
	public class FloatSubtract : FsmStateAction
	{
		[RequiredField]
		public FsmFloat floatVariable;

		[RequiredField]
		public FsmFloat subtract;

		public bool everyFrame;

		public bool perSecond;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoFloatSubtract()
		{
		}
	}
}
