namespace HutongGames.PlayMaker.Actions
{
	public class GetCollisionInfo : FsmStateAction
	{
		public FsmGameObject gameObjectHit;

		public FsmVector3 relativeVelocity;

		public FsmFloat relativeSpeed;

		public FsmVector3 contactPoint;

		public FsmVector3 contactNormal;

		public FsmString physicsMaterialName;

		public override void Reset()
		{
		}

		private void StoreCollisionInfo()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
