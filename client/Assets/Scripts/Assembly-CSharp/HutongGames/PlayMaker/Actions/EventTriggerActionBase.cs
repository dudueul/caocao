using UnityEngine.EventSystems;
using UnityEngine.Events;

namespace HutongGames.PlayMaker.Actions
{
	public abstract class EventTriggerActionBase : ComponentAction<EventTrigger>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmEventTarget eventTarget;

		protected EventTrigger trigger;

		protected EventTrigger.Entry entry;

		public override void Reset()
		{
		}

		protected void Init(EventTriggerType eventTriggerType, UnityAction<BaseEventData> call)
		{
		}

		public override void OnExit()
		{
		}
	}
}
