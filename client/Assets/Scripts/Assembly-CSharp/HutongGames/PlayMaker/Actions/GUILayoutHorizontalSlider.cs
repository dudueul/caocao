namespace HutongGames.PlayMaker.Actions
{
	public class GUILayoutHorizontalSlider : GUILayoutAction
	{
		[RequiredField]
		public FsmFloat floatVariable;

		[RequiredField]
		public FsmFloat leftValue;

		[RequiredField]
		public FsmFloat rightValue;

		public FsmEvent changedEvent;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
