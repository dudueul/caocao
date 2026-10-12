namespace HutongGames.PlayMaker.Actions
{
	public class FloatClamp : FsmStateAction
	{
		[RequiredField]
		public FsmFloat floatVariable;

		[RequiredField]
		public FsmFloat minValue;

		[RequiredField]
		public FsmFloat maxValue;

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

		private void DoClamp()
		{
		}
	}
}
