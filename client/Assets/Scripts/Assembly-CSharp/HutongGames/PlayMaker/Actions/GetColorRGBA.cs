namespace HutongGames.PlayMaker.Actions
{
	public class GetColorRGBA : FsmStateAction
	{
		[RequiredField]
		public FsmColor color;

		public FsmFloat storeRed;

		public FsmFloat storeGreen;

		public FsmFloat storeBlue;

		public FsmFloat storeAlpha;

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

		private void DoGetColorRGBA()
		{
		}
	}
}
