namespace HutongGames.PlayMaker.Actions
{
	[NoActionTargets]
	public class ArrayTransferValue : FsmStateAction
	{
		public enum ArrayTransferType
		{
			Copy = 0,
			Cut = 1,
			nullify = 2
		}

		public enum ArrayPasteType
		{
			AsFirstItem = 0,
			AsLastItem = 1,
			InsertAtSameIndex = 2,
			ReplaceAtSameIndex = 3
		}

		[RequiredField]
		public FsmArray arraySource;

		[RequiredField]
		public FsmArray arrayTarget;

		public FsmInt indexToTransfer;

		public FsmEnum copyType;

		public FsmEnum pasteType;

		public FsmEvent indexOutOfRange;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoTransferValue()
		{
		}
	}
}
