namespace HutongGames.PlayMaker.Actions
{
	public class TransformPoint : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmVector3 localPosition;

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

		private void DoTransformPoint()
		{
		}
	}
}
