namespace HutongGames.PlayMaker.Actions
{
	public class GameObjectIsNull : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject gameObject;

		public FsmEvent isNull;

		public FsmEvent isNotNull;

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

		private void DoIsGameObjectNull()
		{
		}
	}
}
