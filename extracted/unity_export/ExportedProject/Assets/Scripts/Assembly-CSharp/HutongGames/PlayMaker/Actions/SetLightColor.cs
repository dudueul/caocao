using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetLightColor : ComponentAction<Light>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmColor lightColor;

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

		private void DoSetLightColor()
		{
		}
	}
}
