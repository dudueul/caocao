using UnityEngine.UI;

namespace HutongGames.PlayMaker
{
	public class PlayMakerUiFloatValueChangedEvent : PlayMakerUiEventBase
	{
		public Slider slider;

		public Scrollbar scrollbar;

		protected override void Initialize()
		{
		}

		protected void OnDisable()
		{
		}

		private void OnValueChanged(float value)
		{
		}
	}
}
