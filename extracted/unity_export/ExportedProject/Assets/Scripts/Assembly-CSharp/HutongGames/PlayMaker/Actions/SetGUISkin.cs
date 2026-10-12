using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetGUISkin : FsmStateAction
	{
		[RequiredField]
		public GUISkin skin;

		public FsmBool applyGlobally;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}
	}
}
