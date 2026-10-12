namespace HutongGames.PlayMaker.Actions
{
	public class GetTimeInfo : FsmStateAction
	{
		public enum TimeInfo
		{
			DeltaTime = 0,
			TimeScale = 1,
			SmoothDeltaTime = 2,
			TimeInCurrentState = 3,
			TimeSinceStartup = 4,
			TimeSinceLevelLoad = 5,
			RealTimeSinceStartup = 6,
			RealTimeInCurrentState = 7
		}

		public TimeInfo getInfo;

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

		private void DoGetTimeInfo()
		{
		}
	}
}
