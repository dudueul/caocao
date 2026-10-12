using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class iTweenMoveTo : iTweenFsmAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString id;

		public FsmGameObject transformPosition;

		public FsmVector3 vectorPosition;

		public FsmFloat time;

		public FsmFloat delay;

		public FsmFloat speed;

		public Space space;

		public iTween.EaseType easeType;

		public iTween.LoopType loopType;

		public FsmBool orientToPath;

		public FsmGameObject lookAtObject;

		public FsmVector3 lookAtVector;

		public FsmFloat lookTime;

		public AxisRestriction axis;

		public FsmBool moveToPath;

		public FsmFloat lookAhead;

		public FsmGameObject[] transforms;

		public FsmVector3[] vectors;

		public FsmBool reverse;

		private Vector3[] tempVct3;

		public override void OnDrawActionGizmos()
		{
		}

		public override void Reset()
		{
		}

		public override void OnEnter()
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
