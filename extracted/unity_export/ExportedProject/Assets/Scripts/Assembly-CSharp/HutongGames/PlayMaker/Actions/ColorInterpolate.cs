namespace HutongGames.PlayMaker.Actions
{
	public class ColorInterpolate : FsmStateAction
	{
		[RequiredField]
		public FsmColor[] colors;

		[RequiredField]
		public FsmFloat time;

		[RequiredField]
		public FsmColor storeColor;

		public FsmEvent finishEvent;

		public bool realTime;

		private float startTime;

		private float currentTime;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
