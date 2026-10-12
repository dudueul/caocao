using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class UiCanvasEnableRaycast : ComponentAction<PlayMakerCanvasRaycastFilterProxy>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool enableRaycasting;

		public FsmBool resetOnExit;

		public bool everyFrame;

		[SerializeField]
		private PlayMakerCanvasRaycastFilterProxy raycastFilterProxy;

		private bool originalValue;

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

		private void DoAction()
		{
		}

		public override void OnExit()
		{
		}
	}
}
