using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GUILayoutToolbar : GUILayoutAction
	{
		public FsmInt numButtons;

		public FsmInt selectedButton;

		public FsmEvent[] buttonEventsArray;

		public FsmTexture[] imagesArray;

		public FsmString[] textsArray;

		public FsmString[] tooltipsArray;

		public FsmString style;

		public bool everyFrame;

		private GUIContent[] contents;

		public GUIContent[] Contents
		{
			get
			{
				return null;
			}
		}

		private void SetButtonsContent()
		{
		}

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnGUI()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
