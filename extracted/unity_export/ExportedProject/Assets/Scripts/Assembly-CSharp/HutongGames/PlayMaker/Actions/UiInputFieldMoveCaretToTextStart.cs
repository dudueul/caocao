using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiInputFieldMoveCaretToTextStart : ComponentAction<InputField>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool shift;

		private InputField inputField;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoAction()
		{
		}
	}
}
