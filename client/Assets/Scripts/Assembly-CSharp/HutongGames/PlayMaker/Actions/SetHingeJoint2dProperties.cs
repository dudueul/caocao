using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetHingeJoint2dProperties : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool useLimits;

		public FsmFloat min;

		public FsmFloat max;

		public FsmBool useMotor;

		public FsmFloat motorSpeed;

		public FsmFloat maxMotorTorque;

		public bool everyFrame;

		private HingeJoint2D _joint;

		private JointMotor2D _motor;

		private JointAngleLimits2D _limits;

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
