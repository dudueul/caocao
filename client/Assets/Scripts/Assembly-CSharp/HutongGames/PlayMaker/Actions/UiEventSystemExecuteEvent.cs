using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class UiEventSystemExecuteEvent : FsmStateAction
	{
		public enum EventHandlers
		{
			Submit = 0,
			beginDrag = 1,
			cancel = 2,
			deselectHandler = 3,
			dragHandler = 4,
			dropHandler = 5,
			endDragHandler = 6,
			initializePotentialDrag = 7,
			pointerClickHandler = 8,
			pointerDownHandler = 9,
			pointerEnterHandler = 10,
			pointerExitHandler = 11,
			pointerUpHandler = 12,
			scrollHandler = 13,
			submitHandler = 14,
			updateSelectedHandler = 15
		}

		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmEnum eventHandler;

		public FsmEvent success;

		public FsmEvent canNotHandleEvent;

		private GameObject go;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private bool ExecuteEvent()
		{
			return false;
		}
	}
}
