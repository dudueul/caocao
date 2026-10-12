namespace HutongGames.PlayMaker.Actions
{
	public class ColorCompare : FsmStateAction
	{
		[RequiredField]
		public FsmColor color1;

		[RequiredField]
		public FsmColor color2;

		[RequiredField]
		public FsmFloat tolerance;

		public FsmEvent equal;

		public FsmEvent notEqual;

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
