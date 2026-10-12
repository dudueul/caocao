namespace HutongGames.PlayMaker.Actions
{
	public class QuaternionAngleAxis : QuaternionBaseAction
	{
		[RequiredField]
		public FsmFloat angle;

		[RequiredField]
		public FsmVector3 axis;

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

		private void DoQuatAngleAxis()
		{
		}
	}
}
