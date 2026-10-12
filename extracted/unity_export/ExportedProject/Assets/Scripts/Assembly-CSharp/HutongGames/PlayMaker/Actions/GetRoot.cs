namespace HutongGames.PlayMaker.Actions
{
	public class GetRoot : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmGameObject storeRoot;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetRoot()
		{
		}
	}
}
