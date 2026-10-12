namespace HutongGames.PlayMaker.Actions
{
	public class GetFsmState : FsmStateAction
	{
		public PlayMakerFSM fsmComponent;

		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString storeResult;

		public bool everyFrame;

		private PlayMakerFSM fsm;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetFsmState()
		{
		}
	}
}
