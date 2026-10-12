using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class LookAt : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject targetObject;

		public FsmVector3 targetPosition;

		public FsmVector3 upVector;

		public FsmBool keepVertical;

		public FsmBool debug;

		public FsmColor debugLineColor;

		public bool everyFrame;

		private GameObject go;

		private GameObject goTarget;

		private Vector3 lookAtPos;

		private Vector3 lookAtPosWithVertical;

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

		private void DoLookAt()
		{
		}

		public bool UpdateLookAtPosition()
		{
			return false;
		}

		public Vector3 GetLookAtPosition()
		{
			return default(Vector3);
		}

		public Vector3 GetLookAtPositionWithVertical()
		{
			return default(Vector3);
		}
	}
}
