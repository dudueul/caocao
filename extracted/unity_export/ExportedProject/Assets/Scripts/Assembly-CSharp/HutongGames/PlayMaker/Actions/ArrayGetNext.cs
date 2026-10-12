namespace HutongGames.PlayMaker.Actions
{
	public class ArrayGetNext : FsmStateAction
	{
		[RequiredField]
		public FsmArray array;

		public FsmInt startIndex;

		public FsmInt endIndex;

		public FsmEvent loopEvent;

		public FsmBool resetFlag;

		public FsmEvent finishedEvent;

		public FsmVar result;

		public FsmInt currentIndex;

		private int nextItemIndex;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetNextItem()
		{
		}
	}
}
