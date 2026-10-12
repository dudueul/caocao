namespace HutongGames.PlayMaker.Actions
{
	public class GameObjectCompare : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObjectVariable;

		[RequiredField]
		public FsmGameObject compareTo;

		public FsmEvent equalEvent;

		public FsmEvent notEqualEvent;

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

		private void DoGameObjectCompare()
		{
		}
	}
}
