namespace HutongGames.PlayMaker.Actions
{
	public class SampleCurve : FsmStateAction
	{
		[RequiredField]
		public FsmAnimationCurve curve;

		[RequiredField]
		public FsmFloat sampleAt;

		[RequiredField]
		public FsmFloat storeValue;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSampleCurve()
		{
		}
	}
}
