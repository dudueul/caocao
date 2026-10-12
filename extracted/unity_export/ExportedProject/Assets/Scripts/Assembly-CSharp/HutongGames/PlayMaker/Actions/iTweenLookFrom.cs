namespace HutongGames.PlayMaker.Actions
{
	public class iTweenLookFrom : iTweenFsmAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString id;

		public FsmGameObject transformTarget;

		public FsmVector3 vectorTarget;

		public FsmFloat time;

		public FsmFloat delay;

		public FsmFloat speed;

		public iTween.EaseType easeType;

		public iTween.LoopType loopType;

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
