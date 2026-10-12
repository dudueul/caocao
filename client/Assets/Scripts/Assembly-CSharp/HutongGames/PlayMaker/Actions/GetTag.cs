namespace HutongGames.PlayMaker.Actions
{
	public class GetTag : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject gameObject;

		[RequiredField]
		public FsmString storeResult;

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

		private void DoGetTag()
		{
		}
	}
}
