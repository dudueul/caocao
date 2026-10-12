namespace HutongGames.PlayMaker.Actions
{
	public class MousePick2dEvent : FsmStateAction
	{
		public FsmOwnerDefault GameObject;

		public FsmEvent mouseOver;

		public FsmEvent mouseDown;

		public FsmEvent mouseUp;

		public FsmEvent mouseOff;

		public FsmInt[] layerMask;

		public FsmBool invertMask;

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

		private void DoMousePickEvent()
		{
		}

		private bool DoRaycast()
		{
			return false;
		}
	}
}
