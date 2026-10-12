namespace HutongGames.PlayMaker.Actions
{
	public class MouseLook : FsmStateAction
	{
		public enum RotationAxes
		{
			MouseXAndY = 0,
			MouseX = 1,
			MouseY = 2
		}

		[RequiredField]
		public FsmOwnerDefault gameObject;

		public RotationAxes axes;

		[RequiredField]
		public FsmFloat sensitivityX;

		[RequiredField]
		public FsmFloat sensitivityY;

		public FsmFloat minimumX;

		public FsmFloat maximumX;

		public FsmFloat minimumY;

		public FsmFloat maximumY;

		public bool everyFrame;

		private float rotationX;

		private float rotationY;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoMouseLook()
		{
		}

		private float GetXRotation()
		{
			return 0f;
		}

		private float GetYRotation()
		{
			return 0f;
		}

		private static float ClampAngle(float angle, FsmFloat min, FsmFloat max)
		{
			return 0f;
		}
	}
}
