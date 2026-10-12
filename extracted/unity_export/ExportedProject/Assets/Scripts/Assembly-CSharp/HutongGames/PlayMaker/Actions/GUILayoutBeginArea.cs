using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GUILayoutBeginArea : FsmStateAction
	{
		public FsmRect screenRect;

		public FsmFloat left;

		public FsmFloat top;

		public FsmFloat width;

		public FsmFloat height;

		public FsmBool normalized;

		public FsmString style;

		private Rect rect;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
