namespace HutongGames.PlayMaker.Actions
{
	public class QuaternionLerp : QuaternionBaseAction
	{
		[RequiredField]
		public FsmQuaternion fromQuaternion;

		[RequiredField]
		public FsmQuaternion toQuaternion;

		[RequiredField]
		public FsmFloat amount;

		[RequiredField]
		public FsmQuaternion storeResult;

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

		private void DoQuatLerp()
		{
		}
	}
}
