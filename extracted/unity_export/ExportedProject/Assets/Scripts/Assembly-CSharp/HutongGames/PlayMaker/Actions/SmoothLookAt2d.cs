using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SmoothLookAt2d : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject targetObject;

		public FsmVector2 targetPosition2d;

		public FsmVector3 targetPosition;

		public FsmFloat rotationOffset;

		public FsmFloat speed;

		public FsmBool debug;

		public FsmFloat finishTolerance;

		public FsmEvent finishEvent;

		private GameObject previousGo;

		private Quaternion lastRotation;

		private Quaternion desiredRotation;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnLateUpdate()
		{
		}

		private void DoSmoothLookAt()
		{
		}
	}
}
