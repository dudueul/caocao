using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiSliderSetWholeNumbers : ComponentAction<Slider>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool wholeNumbers;

		public FsmBool resetOnExit;

		private Slider slider;

		private bool originalValue;

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
