using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiTransitionSetType : ComponentAction<Selectable>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public Selectable.Transition transition;

		public FsmBool resetOnExit;

		private Selectable selectable;

		private Selectable.Transition originalTransition;

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
