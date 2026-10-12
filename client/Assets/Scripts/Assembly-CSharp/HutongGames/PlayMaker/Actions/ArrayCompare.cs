namespace HutongGames.PlayMaker.Actions
{
	public class ArrayCompare : FsmStateAction
	{
		[RequiredField]
		public FsmArray array1;

		[RequiredField]
		public FsmArray array2;

		public FsmEvent SequenceEqual;

		public FsmEvent SequenceNotEqual;

		public FsmBool storeResult;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSequenceEqual()
		{
		}

		private bool TestSequenceEqual(object[] _array1, object[] _array2)
		{
			return false;
		}
	}
}
