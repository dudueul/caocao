using System;
using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	[Obsolete]
	public class TouchGUIEvent : FsmStateAction
	{
		public enum OffsetOptions
		{
			TopLeft = 0,
			Center = 1,
			TouchStart = 2
		}

		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmInt fingerId;

		public FsmEvent touchBegan;

		public FsmEvent touchMoved;

		public FsmEvent touchStationary;

		public FsmEvent touchEnded;

		public FsmEvent touchCanceled;

		public FsmEvent notTouching;

		public FsmInt storeFingerId;

		public FsmVector3 storeHitPoint;

		public FsmBool normalizeHitPoint;

		public FsmVector3 storeOffset;

		public OffsetOptions relativeTo;

		public FsmBool normalizeOffset;

		public bool everyFrame;

		private Vector3 touchStartPos;

		private GUIElement guiElement;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoTouchGUIEvent()
		{
		}

		private void DoTouch(Touch touch)
		{
		}

		private void DoTouchOffset(Vector3 touchPos)
		{
		}
	}
}
