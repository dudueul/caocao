namespace HutongGames.PlayMaker.Actions
{
	public class GUIHorizontalSlider : GUIAction
	{
		[RequiredField]
		public FsmFloat floatVariable;

		[RequiredField]
		public FsmFloat leftValue;

		[RequiredField]
		public FsmFloat rightValue;

		public FsmString sliderStyle;

		public FsmString thumbStyle;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
