using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class iTweenRotateTo : iTweenFsmAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString id;

		public FsmGameObject transformRotation;

		public FsmVector3 vectorRotation;

		public FsmFloat time;

		public FsmFloat delay;

		public FsmFloat speed;

		public iTween.EaseType easeType;

		public iTween.LoopType loopType;

		public Space space;

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
