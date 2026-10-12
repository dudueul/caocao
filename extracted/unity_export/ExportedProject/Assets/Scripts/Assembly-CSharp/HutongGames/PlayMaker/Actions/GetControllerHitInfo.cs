namespace HutongGames.PlayMaker.Actions
{
	public class GetControllerHitInfo : FsmStateAction
	{
		public FsmGameObject gameObjectHit;

		public FsmVector3 contactPoint;

		public FsmVector3 contactNormal;

		public FsmVector3 moveDirection;

		public FsmFloat moveLength;

		public FsmString physicsMaterialName;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		private void StoreTriggerInfo()
		{
		}

		public override void OnEnter()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
