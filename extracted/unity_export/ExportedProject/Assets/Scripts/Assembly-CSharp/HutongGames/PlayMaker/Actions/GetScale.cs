using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetScale : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector3 vector;

		public FsmFloat xScale;

		public FsmFloat yScale;

		public FsmFloat zScale;

		public Space space;

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

		private void DoGetScale()
		{
		}
	}
}
