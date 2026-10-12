using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiInputFieldGetText : ComponentAction<InputField>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString text;

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

		private void DoGetTextValue()
		{
		}
	}
}
