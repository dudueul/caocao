using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiInputFieldGetTextAsFloat : ComponentAction<InputField>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat value;

		public FsmBool isFloat;

		public FsmEvent isFloatEvent;

		public FsmEvent isNotFloatEvent;

		public bool everyFrame;

		private InputField inputField;

		private float _value;

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
