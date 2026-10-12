using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAudioLoop : ComponentAction<AudioSource>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool loop;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
