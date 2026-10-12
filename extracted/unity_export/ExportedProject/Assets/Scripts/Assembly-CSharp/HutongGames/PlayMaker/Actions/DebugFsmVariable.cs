namespace HutongGames.PlayMaker.Actions
{
	public class DebugFsmVariable : BaseLogAction
	{
		public LogLevel logLevel;

		[HideTypeFilter]
		public FsmVar variable;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
