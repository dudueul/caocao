namespace HutongGames.PlayMaker.Actions
{
	public class GetButton : FsmStateAction
	{
		[RequiredField]
		public FsmString buttonName;

		[RequiredField]
		public FsmBool storeResult;

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

		private void DoGetButton()
		{
		}
	}
}
