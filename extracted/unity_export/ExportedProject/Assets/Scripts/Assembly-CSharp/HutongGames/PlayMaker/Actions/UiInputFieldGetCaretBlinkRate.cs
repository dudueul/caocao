using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiInputFieldGetCaretBlinkRate : ComponentAction<InputField>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat caretBlinkRate;

		public bool everyFrame;

		private InputField inputField;

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
