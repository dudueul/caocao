using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiScrollRectSetVertical : ComponentAction<ScrollRect>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool vertical;

		public FsmBool resetOnExit;

		public bool everyFrame;

		private ScrollRect scrollRect;

		private bool originalValue;

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
