namespace HutongGames.PlayMaker.Actions
{
	public class QuaternionCompare : QuaternionBaseAction
	{
		[RequiredField]
		public FsmQuaternion Quaternion1;

		[RequiredField]
		public FsmQuaternion Quaternion2;

		public FsmBool equal;

		public FsmEvent equalEvent;

		public FsmEvent notEqualEvent;

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

		private void DoQuatCompare()
		{
		}
	}
}
