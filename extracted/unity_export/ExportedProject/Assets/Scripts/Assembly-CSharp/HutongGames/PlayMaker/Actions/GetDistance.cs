namespace HutongGames.PlayMaker.Actions
{
	public class GetDistance : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmGameObject target;

		[RequiredField]
		public FsmFloat storeResult;

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

		private void DoGetDistance()
		{
		}
	}
}
