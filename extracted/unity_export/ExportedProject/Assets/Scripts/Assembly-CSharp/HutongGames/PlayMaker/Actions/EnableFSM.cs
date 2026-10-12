namespace HutongGames.PlayMaker.Actions
{
	public class EnableFSM : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		public FsmBool enable;

		public FsmBool resetOnExit;

		private PlayMakerFSM fsmComponent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoEnableFSM()
		{
		}

		public override void OnExit()
		{
		}
	}
}
