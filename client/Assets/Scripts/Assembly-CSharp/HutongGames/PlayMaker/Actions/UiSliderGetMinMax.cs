using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiSliderGetMinMax : ComponentAction<Slider>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat minValue;

		public FsmFloat maxValue;

		private Slider slider;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetValue()
		{
		}
	}
}
