namespace HutongGames.PlayMaker.Actions
{
	public class TouchObject2dEvent : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmInt fingerId;

		public FsmEvent touchBegan;

		public FsmEvent touchMoved;

		public FsmEvent touchStationary;

		public FsmEvent touchEnded;

		public FsmEvent touchCanceled;

		public FsmInt storeFingerId;

		public FsmVector2 storeHitPoint;

		public override void Reset()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
