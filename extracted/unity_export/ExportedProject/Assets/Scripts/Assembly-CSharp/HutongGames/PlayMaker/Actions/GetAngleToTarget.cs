namespace HutongGames.PlayMaker.Actions
{
	public class GetAngleToTarget : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject targetObject;

		public FsmVector3 targetPosition;

		public FsmBool ignoreHeight;

		[RequiredField]
		public FsmFloat storeAngle;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnLateUpdate()
		{
		}

		private void DoGetAngleToTarget()
		{
		}
	}
}
