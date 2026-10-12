using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class MoveTowards : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject targetObject;

		public FsmVector3 targetPosition;

		public FsmBool ignoreVertical;

		public FsmFloat maxSpeed;

		public FsmFloat finishDistance;

		public FsmEvent finishEvent;

		private GameObject go;

		private GameObject goTarget;

		private Vector3 targetPos;

		private Vector3 targetPosWithVertical;

		public override void Reset()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoMoveTowards()
		{
		}

		public bool UpdateTargetPos()
		{
			return false;
		}

		public Vector3 GetTargetPos()
		{
			return default(Vector3);
		}

		public Vector3 GetTargetPosWithVertical()
		{
			return default(Vector3);
		}
	}
}
