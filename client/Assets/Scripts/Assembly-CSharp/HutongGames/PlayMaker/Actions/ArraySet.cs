namespace HutongGames.PlayMaker.Actions
{
	public class ArraySet : FsmStateAction
	{
		[RequiredField]
		public FsmArray array;

		public FsmInt index;

		[RequiredField]
		public FsmVar value;

		public bool everyFrame;

		public FsmEvent indexOutOfRange;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetValue()
		{
		}
	}
}
