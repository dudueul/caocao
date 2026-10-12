namespace HutongGames.PlayMaker.Actions
{
	public class ArrayDeleteAt : FsmStateAction
	{
		[RequiredField]
		public FsmArray array;

		public FsmInt index;

		public FsmEvent indexOutOfRangeEvent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoDeleteAt()
		{
		}
	}
}
