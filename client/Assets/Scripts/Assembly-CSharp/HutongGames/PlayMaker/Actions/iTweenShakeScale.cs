namespace HutongGames.PlayMaker.Actions
{
	public class iTweenShakeScale : iTweenFsmAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString id;

		[RequiredField]
		public FsmVector3 vector;

		public FsmFloat time;

		public FsmFloat delay;

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
