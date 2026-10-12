using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class DrawTexture : FsmStateAction
	{
		[RequiredField]
		public FsmTexture texture;

		public FsmRect screenRect;

		public FsmFloat left;

		public FsmFloat top;

		public FsmFloat width;

		public FsmFloat height;

		public ScaleMode scaleMode;

		public FsmBool alphaBlend;

		public FsmFloat imageAspect;

		public FsmBool normalized;

		private Rect rect;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
