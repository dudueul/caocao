namespace HutongGames.PlayMaker.Actions
{
	public class TransformDirection : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmVector3 localDirection;

		[RequiredField]
		public FsmVector3 storeResult;

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

		private void DoTransformDirection()
		{
		}
	}
}
