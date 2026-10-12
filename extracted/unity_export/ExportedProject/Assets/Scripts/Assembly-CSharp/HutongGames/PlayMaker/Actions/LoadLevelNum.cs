namespace HutongGames.PlayMaker.Actions
{
	public class LoadLevelNum : FsmStateAction
	{
		[RequiredField]
		public FsmInt levelIndex;

		public bool additive;

		public FsmEvent loadedEvent;

		public FsmBool dontDestroyOnLoad;

		public FsmEvent failedEvent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
