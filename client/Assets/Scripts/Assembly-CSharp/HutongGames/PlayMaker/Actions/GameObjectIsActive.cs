namespace HutongGames.PlayMaker.Actions
{
	public class GameObjectIsActive : FsmStateAction
	{
		public FsmOwnerDefault gameObject;

		public FsmEvent trueEvent;

		public FsmEvent falseEvent;

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

		private void DoIsActive()
		{
		}
	}
}
