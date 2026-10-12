using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiSliderGetNormalizedValue : ComponentAction<Slider>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat value;

		public bool everyFrame;

		private Slider slider;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetValue()
		{
		}
	}
}
