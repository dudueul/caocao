namespace HutongGames.PlayMaker.Actions
{
	public class SetGUIContentColor : FsmStateAction
	{
		[RequiredField]
		public FsmColor contentColor;

		public FsmBool applyGlobally;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
