namespace HutongGames.PlayMaker.Actions
{
	public class SetCollider2dIsTrigger : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool isTrigger;

		public bool setAllColliders;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetIsTrigger()
		{
		}
	}
}
