using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiSliderGetWholeNumbers : ComponentAction<Slider>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool wholeNumbers;

		public FsmEvent isShowingWholeNumbersEvent;

		public FsmEvent isNotShowingWholeNumbersEvent;

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
