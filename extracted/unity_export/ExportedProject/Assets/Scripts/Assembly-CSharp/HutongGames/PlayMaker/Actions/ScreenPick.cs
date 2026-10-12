namespace HutongGames.PlayMaker.Actions
{
	public class ScreenPick : FsmStateAction
	{
		public FsmVector3 screenVector;

		public FsmFloat screenX;

		public FsmFloat screenY;

		public FsmBool normalized;

		[RequiredField]
		public FsmFloat rayDistance;

		public FsmBool storeDidPickObject;

		public FsmGameObject storeGameObject;

		public FsmVector3 storePoint;

		public FsmVector3 storeNormal;

		public FsmFloat storeDistance;

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

		private void DoScreenPick()
		{
		}
	}
}
