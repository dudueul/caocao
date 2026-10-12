namespace HutongGames.PlayMaker.Actions
{
	public class NguiGetJoystickInput : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector2 input;

		public FsmFloat angle;

		public FsmVector3 inputAndAngle;

		public bool everyFrame;

		private UIJoystick _joystick;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void doGetPadInputs()
		{
		}
	}
}
