using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class UiCanvasGroupSetAlpha : ComponentAction<CanvasGroup>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat alpha;

		public FsmBool resetOnExit;

		public bool everyFrame;

		private CanvasGroup component;

		private float originalValue;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetValue()
		{
		}

		public override void OnExit()
		{
		}
	}
}
