using System;
using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	[Obsolete]
	public class SetGUITexture : ComponentAction<GUITexture>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmTexture texture;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
