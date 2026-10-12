namespace HutongGames.PlayMaker.Actions
{
	public class TakeScreenshot : FsmStateAction
	{
		public enum Destination
		{
			MyPictures = 0,
			PersistentDataPath = 1,
			CustomPath = 2
		}

		public Destination destination;

		public FsmString customPath;

		[RequiredField]
		public FsmString filename;

		public FsmBool autoNumber;

		public FsmInt superSize;

		public FsmBool debugLog;

		private int screenshotCount;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
