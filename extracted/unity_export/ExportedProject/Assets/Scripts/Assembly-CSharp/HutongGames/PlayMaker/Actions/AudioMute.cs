namespace HutongGames.PlayMaker.Actions
{
	public class AudioMute : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool mute;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
