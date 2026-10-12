using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiInputFieldOnSubmitEvent : ComponentAction<InputField>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmEventTarget eventTarget;

		public FsmEvent sendEvent;

		public FsmString text;

		private InputField inputField;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		public void DoOnEndEdit(string value)
		{
		}
	}
}
