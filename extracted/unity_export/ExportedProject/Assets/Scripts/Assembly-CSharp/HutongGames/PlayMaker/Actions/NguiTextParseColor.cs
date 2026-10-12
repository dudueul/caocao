namespace HutongGames.PlayMaker.Actions
{
	public class NguiTextParseColor : FsmStateAction
	{
		[RequiredField]
		public FsmString colorString;

		[RequiredField]
		public FsmColor color;

		private string _lastColor;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
