namespace HutongGames.PlayMaker.Actions
{
	public class SetColorRGBA : FsmStateAction
	{
		[RequiredField]
		public FsmColor colorVariable;

		public FsmFloat red;

		public FsmFloat green;

		public FsmFloat blue;

		public FsmFloat alpha;

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

		private void DoSetColorRGBA()
		{
		}
	}
}
