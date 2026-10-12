namespace HutongGames.PlayMaker.Actions
{
	[NoActionTargets]
	public class GetAxisVector : FsmStateAction
	{
		public enum AxisPlane
		{
			XZ = 0,
			XY = 1,
			YZ = 2
		}

		public FsmString horizontalAxis;

		public FsmString verticalAxis;

		public FsmFloat multiplier;

		[RequiredField]
		public AxisPlane mapToPlane;

		public FsmGameObject relativeTo;

		[RequiredField]
		public FsmVector3 storeVector;

		public FsmFloat storeMagnitude;

		public override void Reset()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
