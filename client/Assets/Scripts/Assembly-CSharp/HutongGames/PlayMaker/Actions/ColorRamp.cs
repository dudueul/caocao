namespace HutongGames.PlayMaker.Actions
{
	public class ColorRamp : FsmStateAction
	{
		[RequiredField]
		public FsmColor[] colors;

		[RequiredField]
		public FsmFloat sampleAt;

		[RequiredField]
		public FsmColor storeColor;

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

		private void DoColorRamp()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
