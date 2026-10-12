namespace HutongGames.PlayMaker.Actions
{
	public class PlayRandomSound : FsmStateAction
	{
		public FsmOwnerDefault gameObject;

		public FsmVector3 position;

		public FsmObject[] audioClips;

		public FsmFloat[] weights;

		public FsmFloat volume;

		public FsmBool noRepeat;

		private int randomIndex;

		private int lastIndex;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoPlayRandomClip()
		{
		}
	}
}
