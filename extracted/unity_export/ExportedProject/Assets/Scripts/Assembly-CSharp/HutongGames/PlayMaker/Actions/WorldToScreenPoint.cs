namespace HutongGames.PlayMaker.Actions
{
	public class WorldToScreenPoint : FsmStateAction
	{
		public FsmVector3 worldPosition;

		public FsmFloat worldX;

		public FsmFloat worldY;

		public FsmFloat worldZ;

		public FsmVector3 storeScreenPoint;

		public FsmFloat storeScreenX;

		public FsmFloat storeScreenY;

		public FsmBool normalize;

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

		private void DoWorldToScreenPoint()
		{
		}
	}
}
