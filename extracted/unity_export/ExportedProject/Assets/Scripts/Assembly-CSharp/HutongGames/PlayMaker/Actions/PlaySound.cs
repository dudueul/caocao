namespace HutongGames.PlayMaker.Actions
{
	public class PlaySound : FsmStateAction
	{
		public FsmOwnerDefault gameObject;

		public FsmVector3 position;

		[RequiredField]
		public FsmObject clip;

		public FsmFloat volume;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoPlaySound()
		{
		}
	}
}
