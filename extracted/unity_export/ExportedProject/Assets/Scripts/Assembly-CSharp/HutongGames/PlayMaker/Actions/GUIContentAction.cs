using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public abstract class GUIContentAction : GUIAction
	{
		public FsmTexture image;

		public FsmString text;

		public FsmString tooltip;

		public FsmString style;

		internal GUIContent content;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
