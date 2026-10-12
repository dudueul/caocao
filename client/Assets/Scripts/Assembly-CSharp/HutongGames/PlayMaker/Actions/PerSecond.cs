namespace HutongGames.PlayMaker.Actions
{
	public class PerSecond : FsmStateAction
	{
		[RequiredField]
		public FsmFloat floatValue;

		[RequiredField]
		public FsmFloat storeResult;

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

		private void DoPerSecond()
		{
		}
	}
}
