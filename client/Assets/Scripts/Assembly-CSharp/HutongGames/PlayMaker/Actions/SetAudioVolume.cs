using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAudioVolume : ComponentAction<AudioSource>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat volume;

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

		private void DoSetAudioVolume()
		{
		}
	}
}
