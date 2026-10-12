using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiInputFieldGetWasCanceled : ComponentAction<InputField>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool wasCanceled;

		public FsmEvent wasCanceledEvent;

		public FsmEvent wasNotCanceledEvent;

		private InputField inputField;

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
