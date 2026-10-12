namespace HutongGames.PlayMaker.Actions
{
	public class GetTransform : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject gameObject;

		[RequiredField]
		public FsmObject storeTransform;

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

		private void DoGetGameObjectName()
		{
		}
	}
}
