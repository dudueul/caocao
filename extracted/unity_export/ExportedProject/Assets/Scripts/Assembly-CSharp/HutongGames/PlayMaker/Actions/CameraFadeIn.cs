using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class CameraFadeIn : FsmStateAction
	{
		[RequiredField]
		public FsmColor color;

		[RequiredField]
		public FsmFloat time;

		public FsmEvent finishEvent;

		public bool realTime;

		private float startTime;

		private float currentTime;

		private Color colorLerp;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
