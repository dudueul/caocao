namespace HutongGames.PlayMaker.Actions
{
	public class GUIVerticalSlider : GUIAction
	{
		[RequiredField]
		public FsmFloat floatVariable;

		[RequiredField]
		public FsmFloat topValue;

		[RequiredField]
		public FsmFloat bottomValue;

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
