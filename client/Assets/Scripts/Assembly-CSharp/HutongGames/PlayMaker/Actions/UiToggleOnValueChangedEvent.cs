using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiToggleOnValueChangedEvent : ComponentAction<Toggle>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmEventTarget eventTarget;

		public FsmEvent sendEvent;

		public FsmBool value;

		private Toggle toggle;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		public void DoOnValueChanged(bool _value)
		{
		}
	}
}
