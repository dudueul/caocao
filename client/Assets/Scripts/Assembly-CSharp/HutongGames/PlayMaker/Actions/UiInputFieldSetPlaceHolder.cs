using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiInputFieldSetPlaceHolder : ComponentAction<InputField>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmGameObject placeholder;

		public FsmBool resetOnExit;

		private InputField inputField;

		private Graphic originalValue;

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
