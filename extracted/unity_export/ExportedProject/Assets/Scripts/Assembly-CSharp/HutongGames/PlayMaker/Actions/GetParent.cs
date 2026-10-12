namespace HutongGames.PlayMaker.Actions
{
	public class GetParent : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject storeResult;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
