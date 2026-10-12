using System;
using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	[Obsolete]
	public class SetIsFixedAngle2d : ComponentAction<Rigidbody2D>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool isFixedAngle;

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

		private void DoSetIsFixedAngle()
		{
		}
	}
}
