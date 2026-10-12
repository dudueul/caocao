using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetLightRange : ComponentAction<Light>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat lightRange;

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
