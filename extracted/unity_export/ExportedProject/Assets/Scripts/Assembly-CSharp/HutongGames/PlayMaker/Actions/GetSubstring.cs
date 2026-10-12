namespace HutongGames.PlayMaker.Actions
{
	public class GetSubstring : FsmStateAction
	{
		[RequiredField]
		public FsmString stringVariable;

		[RequiredField]
		public FsmInt startIndex;

		[RequiredField]
		public FsmInt length;

		[RequiredField]
		public FsmString storeResult;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetSubstring()
		{
		}
	}
}
