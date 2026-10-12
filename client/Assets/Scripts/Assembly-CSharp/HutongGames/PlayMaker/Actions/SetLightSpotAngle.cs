using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetLightSpotAngle : ComponentAction<Light>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat lightSpotAngle;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetLightRange()
		{
		}
	}
}
