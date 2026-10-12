namespace HutongGames.PlayMaker.Actions
{
	public class SetGUIAlpha : FsmStateAction
	{
		[RequiredField]
		public FsmFloat alpha;

		public FsmBool applyGlobally;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
