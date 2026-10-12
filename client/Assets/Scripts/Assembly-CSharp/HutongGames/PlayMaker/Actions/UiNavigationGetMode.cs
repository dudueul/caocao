using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiNavigationGetMode : ComponentAction<Selectable>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString navigationMode;

		public FsmEvent automaticEvent;

		public FsmEvent horizontalEvent;

		public FsmEvent verticalEvent;

		public FsmEvent explicitEvent;

		public FsmEvent noNavigationEvent;

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
