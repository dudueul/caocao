using UnityEngine;
using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiScrollRectSetNormalizedPosition : ComponentAction<ScrollRect>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector2 normalizedPosition;

		public FsmFloat horizontalPosition;

		public FsmFloat verticalPosition;

		public FsmBool resetOnExit;

		public bool everyFrame;

		private ScrollRect scrollRect;

		private Vector2 originalValue;

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
