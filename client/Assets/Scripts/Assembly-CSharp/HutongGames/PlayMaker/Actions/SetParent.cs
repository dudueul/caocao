namespace HutongGames.PlayMaker.Actions
{
	public class SetParent : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject parent;

		public FsmBool resetLocalPosition;

		public FsmBool resetLocalRotation;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
