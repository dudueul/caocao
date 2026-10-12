namespace HutongGames.PlayMaker.Actions
{
	public class ArrayGetRandom : FsmStateAction
	{
		[RequiredField]
		public FsmArray array;

		[RequiredField]
		public FsmVar storeValue;

		public FsmInt index;

		public FsmBool noRepeat;

		public bool everyFrame;

		private int randomIndex;

		private int lastIndex;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetRandomValue()
		{
		}
	}
}
