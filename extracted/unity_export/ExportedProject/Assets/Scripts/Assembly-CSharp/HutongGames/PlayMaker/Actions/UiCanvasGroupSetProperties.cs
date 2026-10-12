using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class UiCanvasGroupSetProperties : ComponentAction<CanvasGroup>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat alpha;

		public FsmBool interactable;

		public FsmBool blocksRaycasts;

		public FsmBool ignoreParentGroup;

		public FsmBool resetOnExit;

		public bool everyFrame;

		private CanvasGroup component;

		private float originalAlpha;

		private bool originalInteractable;

		private bool originalBlocksRaycasts;

		private bool originalIgnoreParentGroup;

		public override void Reset()
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
