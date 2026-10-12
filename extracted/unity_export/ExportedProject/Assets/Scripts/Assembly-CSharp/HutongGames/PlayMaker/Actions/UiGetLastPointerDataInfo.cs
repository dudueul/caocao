using UnityEngine.EventSystems;

namespace HutongGames.PlayMaker.Actions
{
	public class UiGetLastPointerDataInfo : FsmStateAction
	{
		public static PointerEventData lastPointerEventData;

		public FsmInt clickCount;

		public FsmFloat clickTime;

		public FsmVector2 delta;

		public FsmBool dragging;

		public FsmEnum inputButton;

		public FsmBool eligibleForClick;

		public FsmGameObject enterEventCamera;

		public FsmGameObject pressEventCamera;

		public FsmBool isPointerMoving;

		public FsmBool isScrolling;

		public FsmGameObject lastPress;

		public FsmGameObject pointerDrag;

		public FsmGameObject pointerEnter;

		public FsmInt pointerId;

		public FsmGameObject pointerPress;

		public FsmVector2 position;

		public FsmVector2 pressPosition;

		public FsmGameObject rawPointerPress;

		public FsmVector2 scrollDelta;

		public FsmBool used;

		public FsmBool useDragThreshold;

		public FsmVector3 worldNormal;

		public FsmVector3 worldPosition;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
