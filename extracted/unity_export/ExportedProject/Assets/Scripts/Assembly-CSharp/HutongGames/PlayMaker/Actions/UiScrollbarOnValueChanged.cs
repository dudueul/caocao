using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiScrollbarOnValueChanged : ComponentAction<Scrollbar>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmEventTarget eventTarget;

		public FsmEvent sendEvent;

		public FsmFloat value;

		private Scrollbar scrollbar;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		public void DoOnValueChanged(float _value)
		{
		}
	}
}
