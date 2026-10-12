using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class iTweenMoveBy : iTweenFsmAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString id;

		[RequiredField]
		public FsmVector3 vector;

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
