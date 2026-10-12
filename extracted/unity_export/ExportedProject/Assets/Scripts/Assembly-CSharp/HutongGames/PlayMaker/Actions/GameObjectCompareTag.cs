namespace HutongGames.PlayMaker.Actions
{
	public class GameObjectCompareTag : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject gameObject;

		[RequiredField]
		public FsmString tag;

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

		private void DoCompareTag()
		{
		}
	}
}
