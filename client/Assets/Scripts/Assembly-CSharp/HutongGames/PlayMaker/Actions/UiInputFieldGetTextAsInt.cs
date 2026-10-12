using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiInputFieldGetTextAsInt : ComponentAction<InputField>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt value;

		public FsmBool isInt;

		public FsmEvent isIntEvent;

		public FsmEvent isNotIntEvent;

		public bool everyFrame;

		private InputField inputField;

		private int _value;

		private bool _success;

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
