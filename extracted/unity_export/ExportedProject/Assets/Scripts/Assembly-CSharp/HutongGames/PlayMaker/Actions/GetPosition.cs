using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetPosition : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector3 vector;

		public FsmFloat x;

		public FsmFloat y;

		public FsmFloat z;

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

		private void DoGetPosition()
		{
		}
	}
}
