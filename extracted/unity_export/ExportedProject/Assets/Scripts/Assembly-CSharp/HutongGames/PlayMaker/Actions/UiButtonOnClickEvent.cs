using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiButtonOnClickEvent : ComponentAction<Button>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmEventTarget eventTarget;

		public FsmEvent sendEvent;

		private Button button;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		public void DoOnClick()
		{
		}
	}
}
