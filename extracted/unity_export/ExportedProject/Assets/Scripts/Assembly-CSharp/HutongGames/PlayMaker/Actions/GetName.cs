namespace HutongGames.PlayMaker.Actions
{
	public class GetName : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject gameObject;

		[RequiredField]
		public FsmString storeName;

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
