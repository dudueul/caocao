namespace HutongGames.PlayMaker.Actions
{
	public class GetChildCount : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt storeResult;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetChildCount()
		{
		}
	}
}
