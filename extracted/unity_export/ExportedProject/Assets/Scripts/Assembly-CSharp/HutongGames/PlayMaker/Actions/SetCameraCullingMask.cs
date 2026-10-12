using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetCameraCullingMask : ComponentAction<Camera>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmInt[] cullingMask;

		public FsmBool invertMask;

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

		private void DoSetCameraCullingMask()
		{
		}
	}
}
