namespace HutongGames.PlayMaker.Actions
{
	public class SetJointConnectedBody : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault joint;

		public FsmGameObject rigidBody;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
