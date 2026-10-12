namespace HutongGames.PlayMaker.Actions
{
	public class GetMouseButton : FsmStateAction
	{
		[RequiredField]
		public MouseButton button;

		[RequiredField]
		public FsmBool storeResult;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
