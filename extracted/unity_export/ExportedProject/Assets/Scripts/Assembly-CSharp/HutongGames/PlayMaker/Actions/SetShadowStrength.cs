using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetShadowStrength : ComponentAction<Light>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat shadowStrength;

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

		private void DoSetShadowStrength()
		{
		}
	}
}
