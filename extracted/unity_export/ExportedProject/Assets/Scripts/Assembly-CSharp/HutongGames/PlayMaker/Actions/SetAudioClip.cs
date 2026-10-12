using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAudioClip : ComponentAction<AudioSource>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmObject audioClip;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
