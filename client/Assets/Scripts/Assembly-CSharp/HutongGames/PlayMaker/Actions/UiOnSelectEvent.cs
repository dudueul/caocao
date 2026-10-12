using UnityEngine.EventSystems;

namespace HutongGames.PlayMaker.Actions
{
	public class UiOnSelectEvent : EventTriggerActionBase
	{
		public FsmEvent onSelectEvent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void OnSelectDelegate(BaseEventData data)
		{
		}
	}
}
