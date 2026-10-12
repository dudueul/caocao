using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiInputFieldSetText : ComponentAction<InputField>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString text;

		public FsmBool resetOnExit;

		public bool everyFrame;

		private InputField inputField;

		private string originalString;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetTextValue()
		{
		}

		public override void OnExit()
		{
		}
	}
}
