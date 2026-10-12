using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class iTweenMoveUpdate : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject transformPosition;

		public FsmVector3 vectorPosition;

		public FsmFloat time;

		public Space space;

		public FsmBool orientToPath;

		public FsmGameObject lookAtObject;

		public FsmVector3 lookAtVector;

		public FsmFloat lookTime;

		public iTweenFsmAction.AxisRestriction axis;

		private itDic hash;

		private GameObject go;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		public override void OnExit()
		{
		}

		private void DoiTween()
		{
		}
	}
}
