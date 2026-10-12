namespace HutongGames.PlayMaker.Actions
{
	public class FloatCompare : FsmStateAction
	{
		[RequiredField]
		public FsmFloat float1;

		[RequiredField]
		public FsmFloat float2;

		[RequiredField]
		public FsmFloat tolerance;

		public FsmEvent equal;

		public FsmEvent lessThan;

		public FsmEvent greaterThan;

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

		private void DoCompare()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
