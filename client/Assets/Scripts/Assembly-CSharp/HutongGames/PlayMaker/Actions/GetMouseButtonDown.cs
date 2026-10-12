namespace HutongGames.PlayMaker.Actions
{
	public class GetMouseButtonDown : FsmStateAction
	{
		[RequiredField]
		public MouseButton button;

		public FsmEvent sendEvent;

		public FsmBool storeResult;

		public bool inUpdateOnly;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetMouseButtonDown()
		{
		}
	}
}
