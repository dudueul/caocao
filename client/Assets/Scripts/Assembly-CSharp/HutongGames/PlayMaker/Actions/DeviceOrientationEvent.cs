using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class DeviceOrientationEvent : FsmStateAction
	{
		public DeviceOrientation orientation;

		public FsmEvent sendEvent;

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

		private void DoDetectDeviceOrientation()
		{
		}
	}
}
