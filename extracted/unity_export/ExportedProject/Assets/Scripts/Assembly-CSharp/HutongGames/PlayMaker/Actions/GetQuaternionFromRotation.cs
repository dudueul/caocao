namespace HutongGames.PlayMaker.Actions
{
	public class GetQuaternionFromRotation : QuaternionBaseAction
	{
		[RequiredField]
		public FsmVector3 fromDirection;

		[RequiredField]
		public FsmVector3 toDirection;

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

		private void DoQuatFromRotation()
		{
		}
	}
}
