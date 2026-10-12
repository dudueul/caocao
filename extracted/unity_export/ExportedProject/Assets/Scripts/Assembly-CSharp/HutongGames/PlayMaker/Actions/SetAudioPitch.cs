using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAudioPitch : ComponentAction<AudioSource>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat pitch;

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

		private void DoSetAudioPitch()
		{
		}
	}
}
