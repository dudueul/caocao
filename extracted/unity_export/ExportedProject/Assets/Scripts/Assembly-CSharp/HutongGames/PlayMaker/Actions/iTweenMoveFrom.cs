using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class iTweenMoveFrom : iTweenFsmAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString id;

		public FsmGameObject transformPosition;

		public FsmVector3 vectorPosition;

		public FsmFloat time;

		public FsmFloat delay;

		public FsmFloat speed;

		public iTween.EaseType easeType;

		public iTween.LoopType loopType;

		public Space space;

		public FsmBool orientToPath;

		public FsmGameObject lookAtObject;

		public FsmVector3 lookAtVector;

		public FsmFloat lookTime;

		public AxisRestriction axis;

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
