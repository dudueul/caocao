using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class AudioPlay : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat volume;

		public FsmObject oneShotClip;

		public FsmBool WaitForEndOfClip;

		public FsmEvent finishedEvent;

		private AudioSource audio;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
