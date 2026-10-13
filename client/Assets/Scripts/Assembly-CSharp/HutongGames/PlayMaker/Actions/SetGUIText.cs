using UnityEngine;

#if !UNITY_2019_1_OR_NEWER && !UNITY_6000_0_OR_NEWER
using System;
using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	[Obsolete]
	public class SetGUIText : ComponentAction<GUIText>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString text;

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

		private void DoSetGUIText()
		{
		}
	}
}

#endif
