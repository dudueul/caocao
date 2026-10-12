using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Blink : ComponentAction<Renderer>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat timeOff;

		public FsmFloat timeOn;

		public FsmBool startOn;

		public bool rendererOnly;

		public bool realTime;

		private float startTime;

		private float timer;

		private bool blinkOn;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void UpdateBlinkState(bool state)
		{
		}
	}
}
