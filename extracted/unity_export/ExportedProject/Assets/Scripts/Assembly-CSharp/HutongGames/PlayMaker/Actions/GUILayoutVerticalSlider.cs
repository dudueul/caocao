namespace HutongGames.PlayMaker.Actions
{
	public class GUILayoutVerticalSlider : GUILayoutAction
	{
		[RequiredField]
		public FsmFloat floatVariable;

		[RequiredField]
		public FsmFloat topValue;

		[RequiredField]
		public FsmFloat bottomValue;

		public FsmEvent changedEvent;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
