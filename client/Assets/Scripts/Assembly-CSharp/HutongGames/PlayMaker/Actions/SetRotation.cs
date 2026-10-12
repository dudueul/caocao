using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetRotation : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmQuaternion quaternion;

		public FsmVector3 vector;

		public FsmFloat xAngle;

		public FsmFloat yAngle;

		public FsmFloat zAngle;

		public Space space;

		public bool everyFrame;

		public bool lateUpdate;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
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

		private void DoSetRotation()
		{
		}
	}
}
