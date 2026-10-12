namespace HutongGames.PlayMaker.Actions
{
	public class SetGUIBackgroundColor : FsmStateAction
	{
		[RequiredField]
		public FsmColor backgroundColor;

		public FsmBool applyGlobally;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
