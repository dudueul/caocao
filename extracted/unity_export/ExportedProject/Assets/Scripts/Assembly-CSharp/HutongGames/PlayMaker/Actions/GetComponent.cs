namespace HutongGames.PlayMaker.Actions
{
	public class GetComponent : FsmStateAction
	{
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmObject storeComponent;

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

		private void DoGetComponent()
		{
		}
	}
}
