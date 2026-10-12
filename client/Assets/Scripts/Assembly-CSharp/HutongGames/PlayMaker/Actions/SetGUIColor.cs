namespace HutongGames.PlayMaker.Actions
{
	public class SetGUIColor : FsmStateAction
	{
		[RequiredField]
		public FsmColor color;

		public FsmBool applyGlobally;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
