using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiGetColorBlock : ComponentAction<Selectable>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat fadeDuration;

		public FsmFloat colorMultiplier;

		public FsmColor normalColor;

		public FsmColor pressedColor;

		public FsmColor highlightedColor;

		public FsmColor disabledColor;

		public bool everyFrame;

		private Selectable selectable;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetValue()
		{
		}
	}
}
