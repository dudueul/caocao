using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectOverlaps : FsmStateAction
	{
		[RequiredField]
		public FsmRect rect1;

		[RequiredField]
		public FsmRect rect2;

		public FsmEvent trueEvent;

		public FsmEvent falseEvent;

		public FsmBool storeResult;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoRectOverlap()
		{
		}

		public static bool Intersect(Rect a, Rect b)
		{
			return false;
		}

		public static void FlipNegative(ref Rect r)
		{
		}
	}
}
