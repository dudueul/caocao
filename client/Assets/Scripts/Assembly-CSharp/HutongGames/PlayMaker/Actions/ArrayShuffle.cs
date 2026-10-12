namespace HutongGames.PlayMaker.Actions
{
	public class ArrayShuffle : FsmStateAction
	{
		[RequiredField]
		public FsmArray array;

		public FsmInt startIndex;

		public FsmInt shufflingRange;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
