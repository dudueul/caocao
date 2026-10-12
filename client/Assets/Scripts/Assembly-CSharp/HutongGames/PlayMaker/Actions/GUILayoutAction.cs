using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public abstract class GUILayoutAction : FsmStateAction
	{
		public LayoutOption[] layoutOptions;

		private GUILayoutOption[] options;

		public GUILayoutOption[] LayoutOptions
		{
			get
			{
				return null;
			}
		}

		public override void Reset()
		{
		}
	}
}
