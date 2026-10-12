namespace HutongGames.PlayMaker.Actions
{
	public class ScaleGUI : FsmStateAction
	{
		[RequiredField]
		public FsmFloat scaleX;

		[RequiredField]
		public FsmFloat scaleY;

		[RequiredField]
		public FsmFloat pivotX;

		[RequiredField]
		public FsmFloat pivotY;

		public bool normalized;

		public bool applyGlobally;

		private bool applied;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
