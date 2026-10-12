namespace HutongGames.PlayMaker.Actions
{
	public class DeviceShakeEvent : FsmStateAction
	{
		[RequiredField]
		public FsmFloat shakeThreshold;

		[RequiredField]
		public FsmEvent sendEvent;

		public override void Reset()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
