namespace HutongGames.PlayMaker.Actions
{
	public class GUILayoutBeginScrollView : GUILayoutAction
	{
		[RequiredField]
		public FsmVector2 scrollPosition;

		public FsmBool horizontalScrollbar;

		public FsmBool verticalScrollbar;

		public FsmBool useCustomStyle;

		public FsmString horizontalStyle;

		public FsmString verticalStyle;

		public FsmString backgroundStyle;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
