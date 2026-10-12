using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetLightIntensity : ComponentAction<Light>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat lightIntensity;

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

		private void DoSetLightIntensity()
		{
		}
	}
}
