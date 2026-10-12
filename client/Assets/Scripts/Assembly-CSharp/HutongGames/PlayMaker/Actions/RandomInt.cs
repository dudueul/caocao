namespace HutongGames.PlayMaker.Actions
{
	public class RandomInt : FsmStateAction
	{
		[RequiredField]
		public FsmInt min;

		[RequiredField]
		public FsmInt max;

		[RequiredField]
		public FsmInt storeResult;

		public bool inclusiveMax;

		public FsmBool noRepeat;

		private int randomIndex;

		private int lastIndex;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void PickRandom()
		{
		}
	}
}
