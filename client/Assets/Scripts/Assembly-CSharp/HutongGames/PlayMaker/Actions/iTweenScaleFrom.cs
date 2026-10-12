namespace HutongGames.PlayMaker.Actions
{
	public class iTweenScaleFrom : iTweenFsmAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString id;

		public FsmGameObject transformScale;

		public FsmVector3 vectorScale;

		public FsmFloat time;

		public FsmFloat delay;

		public FsmFloat speed;

		public iTween.EaseType easeType;

		public iTween.LoopType loopType;

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
