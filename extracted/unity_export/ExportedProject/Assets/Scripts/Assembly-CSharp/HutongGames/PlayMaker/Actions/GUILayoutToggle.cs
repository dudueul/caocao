namespace HutongGames.PlayMaker.Actions
{
	public class GUILayoutToggle : GUILayoutAction
	{
		[RequiredField]
		public FsmBool storeButtonState;

		public FsmTexture image;

		public FsmString text;

		public FsmString tooltip;

		public FsmString style;

		public FsmEvent changedEvent;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
