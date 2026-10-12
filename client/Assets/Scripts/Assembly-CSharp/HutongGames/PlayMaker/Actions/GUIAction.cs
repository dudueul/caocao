using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public abstract class GUIAction : FsmStateAction
	{
		public FsmRect screenRect;

		public FsmFloat left;

		public FsmFloat top;

		public FsmFloat width;

		public FsmFloat height;

		[RequiredField]
		public FsmBool normalized;

		internal Rect rect;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
