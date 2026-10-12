namespace HutongGames.PlayMaker.Actions
{
	public class TouchObjectEvent : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat pickDistance;

		public FsmInt fingerId;

		public FsmEvent touchBegan;

		public FsmEvent touchMoved;

		public FsmEvent touchStationary;

		public FsmEvent touchEnded;

		public FsmEvent touchCanceled;

		public FsmInt storeFingerId;

		public FsmVector3 storeHitPoint;

		public FsmVector3 storeHitNormal;

		public override void Reset()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
