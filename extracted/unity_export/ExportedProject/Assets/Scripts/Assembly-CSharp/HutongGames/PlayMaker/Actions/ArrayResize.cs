namespace HutongGames.PlayMaker.Actions
{
	public class ArrayResize : FsmStateAction
	{
		[RequiredField]
		public FsmArray array;

		public FsmInt newSize;

		public FsmEvent sizeOutOfRangeEvent;

		public override void OnEnter()
		{
		}
	}
}
