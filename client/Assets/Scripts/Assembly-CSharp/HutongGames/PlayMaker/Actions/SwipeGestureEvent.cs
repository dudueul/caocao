using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SwipeGestureEvent : FsmStateAction
	{
		public FsmFloat minSwipeDistance;

		public FsmEvent swipeLeftEvent;

		public FsmEvent swipeRightEvent;

		public FsmEvent swipeUpEvent;

		public FsmEvent swipeDownEvent;

		private float screenDiagonalSize;

		private float minSwipeDistancePixels;

		private bool touchStarted;

		private Vector2 touchStartPos;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void TestForSwipeGesture(Touch touch)
		{
		}
	}
}
