using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiGetIsInteractable : ComponentAction<Selectable>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool isInteractable;

		public FsmEvent isInteractableEvent;

		public FsmEvent isNotInteractableEvent;

		private Selectable selectable;

		private bool originalState;

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
