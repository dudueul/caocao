using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiSliderOnValueChangedEvent : ComponentAction<Slider>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmEventTarget eventTarget;

		public FsmEvent sendEvent;

		public FsmFloat value;

		private Slider slider;

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
