using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiSliderSetDirection : ComponentAction<Slider>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmEnum direction;

		public FsmBool includeRectLayouts;

		public FsmBool resetOnExit;

		private Slider slider;

		private Slider.Direction originalValue;

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
