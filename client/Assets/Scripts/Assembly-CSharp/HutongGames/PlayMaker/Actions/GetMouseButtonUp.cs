namespace HutongGames.PlayMaker.Actions
{
	public class GetMouseButtonUp : FsmStateAction
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

		public void DoGetMouseButtonUp()
		{
		}
	}
}
