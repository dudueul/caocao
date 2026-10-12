using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiSetAnimationTriggers : ComponentAction<Selectable>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString normalTrigger;

		public FsmString highlightedTrigger;

		public FsmString pressedTrigger;

		public FsmString disabledTrigger;

		public FsmBool resetOnExit;

		private Selectable selectable;

		private AnimationTriggers _animationTriggers;

		private AnimationTriggers originalAnimationTriggers;

		public override void Reset()
		{
		}

		public override void OnEnter()
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
