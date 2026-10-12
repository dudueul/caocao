namespace HutongGames.PlayMaker.Actions
{
	public class AnimateFloat : FsmStateAction
	{
		[RequiredField]
		public FsmAnimationCurve animCurve;

		[RequiredField]
		public FsmFloat floatVariable;

		public FsmEvent finishEvent;

		public bool realTime;

		private float startTime;

		private float currentTime;

		private float endTime;

		private bool looping;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
