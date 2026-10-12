using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiInputFieldDeactivate : ComponentAction<InputField>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool activateOnExit;

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

		public override void OnExit()
		{
		}
	}
}
