namespace HutongGames.PlayMaker.Actions
{
	public class GetLayer : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject gameObject;

		[RequiredField]
		public FsmInt storeResult;

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

		private void DoGetLayer()
		{
		}
	}
}
