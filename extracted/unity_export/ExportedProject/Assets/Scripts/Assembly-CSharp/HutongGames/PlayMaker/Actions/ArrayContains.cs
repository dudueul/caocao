namespace HutongGames.PlayMaker.Actions
{
	public class ArrayContains : FsmStateAction
	{
		[RequiredField]
		public FsmArray array;

		[RequiredField]
		public FsmVar value;

		public FsmInt index;

		public FsmBool isContained;

		public FsmEvent isContainedEvent;

		public FsmEvent isNotContainedEvent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoCheckContainsValue()
		{
		}
	}
}
