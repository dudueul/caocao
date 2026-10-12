namespace HutongGames.PlayMaker.Actions
{
	public class iTweenColorTo : iTweenFsmAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString id;

		public FsmBool useColor;

		public FsmColor color;

		public FsmBool useIndividualColor;

		public FsmFloat r;

		public FsmFloat g;

		public FsmFloat b;

		public FsmFloat a;

		public FsmString namedValueColor;

		public FsmBool includeChildren;

		public FsmFloat time;

		public FsmFloat delay;

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
