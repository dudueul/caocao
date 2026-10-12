namespace HutongGames.PlayMaker.Actions
{
	public class MoveObject : EaseFsmAction
	{
		[RequiredField]
		public FsmOwnerDefault objectToMove;

		[RequiredField]
		public FsmGameObject destination;

		private FsmVector3 fromValue;

		private FsmVector3 toVector;

		private FsmVector3 fromVector;

		private bool finishInNextStep;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
