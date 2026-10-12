using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class CutToCamera : FsmStateAction
	{
		[RequiredField]
		public Camera camera;

		public bool makeMainCamera;

		public bool cutBackOnExit;

		private Camera oldCamera;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		private static void SwitchCamera(Camera camera1, Camera camera2)
		{
		}
	}
}
