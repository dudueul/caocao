namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformFlipLayoutAxis : FsmStateAction
	{
		public enum RectTransformFlipOptions
		{
			Horizontal = 0,
			Vertical = 1,
			Both = 2
		}

		[RequiredField]
		public FsmOwnerDefault gameObject;

		public RectTransformFlipOptions axis;

		public FsmBool keepPositioning;

		public FsmBool recursive;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoFlip()
		{
		}
	}
}
