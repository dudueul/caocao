namespace HutongGames.PlayMaker.Actions
{
	public class QuaternionLookRotation : QuaternionBaseAction
	{
		[RequiredField]
		public FsmVector3 direction;

		public FsmVector3 upVector;

		[RequiredField]
		public FsmQuaternion result;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		public override void OnLateUpdate()
		{
		}

		public override void OnFixedUpdate()
		{
		}

		private void DoQuatLookRotation()
		{
		}
	}
}
