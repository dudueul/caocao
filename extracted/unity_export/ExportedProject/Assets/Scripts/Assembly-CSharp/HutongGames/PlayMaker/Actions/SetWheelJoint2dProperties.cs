using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetWheelJoint2dProperties : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool useMotor;

		public FsmFloat motorSpeed;

		public FsmFloat maxMotorTorque;

		public FsmFloat angle;

		public FsmFloat dampingRatio;

		public FsmFloat frequency;

		public bool everyFrame;

		private WheelJoint2D _wj2d;

		private JointMotor2D _motor;

		private JointSuspension2D _suspension;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void SetProperties()
		{
		}
	}
}
