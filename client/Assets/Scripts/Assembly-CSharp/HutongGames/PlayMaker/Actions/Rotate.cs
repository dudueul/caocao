using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Rotate : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector3 vector;

		public FsmFloat xAngle;

		public FsmFloat yAngle;

		public FsmFloat zAngle;

		public Space space;

		public bool perSecond;

		public bool everyFrame;

		public bool lateUpdate;

		public bool fixedUpdate;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		public override void OnLateUpdate()
		{
		}

		public override void OnFixedUpdate()
		{
		}

		private void DoRotate()
		{
		}
	}
}
