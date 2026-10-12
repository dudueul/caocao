using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiTransitionGetType : ComponentAction<Selectable>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString transition;

		public FsmEvent colorTintEvent;

		public FsmEvent spriteSwapEvent;

		public FsmEvent animationEvent;

		public FsmEvent noTransitionEvent;

		private Selectable selectable;

		private Selectable.Transition originalTransition;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetValue()
		{
		}
	}
}
