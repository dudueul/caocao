namespace HutongGames.PlayMaker.Actions
{
	public class FindChild : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString childName;

		[RequiredField]
		public FsmGameObject storeResult;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoFindChild()
		{
		}
	}
}
