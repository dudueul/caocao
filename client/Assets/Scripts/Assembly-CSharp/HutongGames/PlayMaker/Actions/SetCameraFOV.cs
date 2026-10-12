using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetCameraFOV : ComponentAction<Camera>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat fieldOfView;

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

		private void DoSetCameraFOV()
		{
		}
	}
}
