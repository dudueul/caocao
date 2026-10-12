using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SmoothLookAtDirection : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmVector3 targetDirection;

		public FsmFloat minMagnitude;

		public FsmVector3 upVector;

		[RequiredField]
		public FsmBool keepVertical;

		[RequiredField]
		public FsmFloat speed;

		public bool lateUpdate;

		public FsmEvent finishEvent;

		public FsmBool finish;

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

		public override void OnUpdate()
		{
		}

		public override void OnLateUpdate()
		{
		}

		private void DoSmoothLookAtDirection()
		{
		}
	}
}
