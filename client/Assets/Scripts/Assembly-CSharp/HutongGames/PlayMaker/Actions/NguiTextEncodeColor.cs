using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class NguiTextEncodeColor : FsmStateAction
	{
		[RequiredField]
		public FsmColor color;

		[RequiredField]
		public FsmString colorString;

		private Color _lastColor;

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
	}
}
