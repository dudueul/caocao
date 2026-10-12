using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiSliderSetMinMax : ComponentAction<Slider>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat minValue;

		public FsmFloat maxValue;

		public FsmBool resetOnExit;

		public bool everyFrame;

		private Slider slider;

		private float originalMinValue;

		private float originalMaxValue;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
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
