using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiSetColorBlock : ComponentAction<Selectable>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat fadeDuration;

		public FsmFloat colorMultiplier;

		public FsmColor normalColor;

		public FsmColor pressedColor;

		public FsmColor highlightedColor;

		public FsmColor disabledColor;

		public FsmBool resetOnExit;

		public bool everyFrame;

		private Selectable selectable;

		private ColorBlock _colorBlock;

		private ColorBlock originalColorBlock;

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
