using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiToggleSetIsOn : ComponentAction<Toggle>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool isOn;

		public FsmBool resetOnExit;

		private Toggle _toggle;

		private bool _originalValue;

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
