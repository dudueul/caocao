namespace HutongGames.PlayMaker.Actions
{
	public class FloatAdd : FsmStateAction
	{
		[RequiredField]
		public FsmFloat floatVariable;

		[RequiredField]
		public FsmFloat add;

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

		private void DoFloatAdd()
		{
		}
	}
}
