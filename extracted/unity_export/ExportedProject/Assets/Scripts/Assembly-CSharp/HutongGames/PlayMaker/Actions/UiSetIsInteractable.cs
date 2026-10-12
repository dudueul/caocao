using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiSetIsInteractable : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool isInteractable;

		public FsmBool resetOnExit;

		private Selectable _selectable;

		private bool _originalState;

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
